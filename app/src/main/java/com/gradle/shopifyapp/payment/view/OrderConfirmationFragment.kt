package com.gradle.shopifyapp.payment.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentOrderConfirmationBinding
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price
import com.gradle.shopifyapp.model.*
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModel
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModelFactory
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import com.paypal.android.sdk.payments.PayPalConfiguration
import com.paypal.android.sdk.payments.*
import com.paypal.android.sdk.payments.PayPalService
import com.paypal.android.sdk.payments.PaymentActivity
import com.paypal.android.sdk.payments.PaymentConfirmation
import org.json.JSONException
import org.json.JSONObject
import java.math.BigDecimal

class OrderConfirmationFragment : Fragment() {
    private var _binding: FragmentOrderConfirmationBinding? = null
    private val binding get() = _binding!!
    lateinit var preference: MyPreference

    var amount:String?=null
    lateinit var orderConfirmationVmFactory: OrderConfirmationViewModelFactory
    lateinit var orderConfirmationVm: OrderConfirmationViewModel


    lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var shoppingCartVm: ShoppingCartViewModel

    var line_items = ArrayList<LineItem>()
    var total_prices = ArrayList<Total_price>()
    var tax =0.0
    var totalPrice = 0.0
    var subtotal = 0.0
    var discount = 0.0
    var discountCode = DiscountCode()
    var myLineItems = mutableListOf<OrderModel.LineItem>()
    var draftOrderIds = ArrayList<Long>()




    var myAddress :Addresse = Addresse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderConfirmationBinding.inflate(inflater, container, false)
        preference = MyPreference.getInstance(requireContext())!!
        vmFactory = ShoppingCartViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        shoppingCartVm = ViewModelProvider(this, vmFactory).get(ShoppingCartViewModel::class.java)

        _binding!!.backBtn.setOnClickListener {
            findNavController(this)?.navigate(R.id.paymenttoaddress)
        }

        _binding!!.nameTextView.text = preference.getData(Constants.USERFIRSTNAME)
        _binding!!.emailTextView.text = preference.getData(Constants.USEREMAIL)
        _binding!!.phoneTextView.text = preference.getData(Constants.USERMOBILEPHONE)

        line_items = (requireActivity() as com.gradle.shopifyapp.payment.view.PaymentActivity).lineItems
        total_prices = (requireActivity() as com.gradle.shopifyapp.payment.view.PaymentActivity).totalPrice
        draftOrderIds = (requireActivity() as com.gradle.shopifyapp.payment.view.PaymentActivity).draftOrderId



        for(i in 0..total_prices.size-1){
            subtotal += (total_prices[i].subtotal!!.toDouble()* (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
            totalPrice += (total_prices[i].total!!.toDouble() * (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
            tax += (total_prices[i].tax!!.toDouble() *(preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
        }
        amount = totalPrice.toString()
        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount))) + preference.getData(Constants.TOCURRENCY)
        _binding!!.shipping.text = (String.format("%.2f",tax)) + preference.getData(Constants.TOCURRENCY)
        _binding!!.subtotal.text = (String.format("%.2f",subtotal)) + preference.getData(Constants.TOCURRENCY)


        orderConfirmationVmFactory = OrderConfirmationViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        orderConfirmationVm = ViewModelProvider(this, orderConfirmationVmFactory).get(OrderConfirmationViewModel::class.java)


        _binding!!.verify.setOnClickListener{
            orderConfirmationVm.getAllDiscountCodes(requireContext())
            orderConfirmationVm.liveDiscountList.observe(viewLifecycleOwner){discountCodes ->
                for(i in 0..discountCodes.discount_codes.size-1){
                    if(_binding!!.couponEditText.text.toString() == discountCodes.discount_codes[i].code){
                        discount = subtotal*0.1
                        _binding!!.discount.text = "-" + (String.format("%.2f",(discount))) + preference.getData(Constants.TOCURRENCY)
                        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount))) + preference.getData(Constants.TOCURRENCY)
                        _binding!!.verify.setImageResource(R.drawable.verify_checked_icon)
                        discountCode = discountCodes.discount_codes[i]
                        ////////
                        break
                    }else{
                        discount = 0.0
                        _binding!!.discount.text = discount.toString() + preference.getData(Constants.TOCURRENCY)
                        _binding!!.verify.setImageResource(R.drawable.verify_icon)
                        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount))) + preference.getData(Constants.TOCURRENCY)
                    }
                }
            }
        }

        _binding!!.orderBtn.setOnClickListener {

            //line items email discount code shipping address method for payment
            var result_order_Model = makeOrderModel()

            if(_binding!!.creditRadioBtn.isChecked )
                getPayment()
            if(!_binding!!.creditRadioBtn.isChecked && !_binding!!.cashRadioBtn.isChecked)
                Toast.makeText(requireContext(),"Choose a payment method!",Toast.LENGTH_LONG).show()
            if(_binding!!.cashRadioBtn.isChecked)
            {
                if(InternetConnection.isInternetAvailable(requireContext())){
                    orderConfirmationVm.postOrder(result_order_Model)
                    binding.progressbar.visibility = View.VISIBLE
                    orderConfirmationVm.liveOrderModel.observe(viewLifecycleOwner){
                        if (it.isSuccessful){
                            for (id in draftOrderIds){
                                shoppingCartVm.deleteProductFromDraftOrder(id.toString())
                            }
                            binding.progressbar.visibility = View.GONE
                            Toast.makeText(requireContext(),"Your Order Is Added Successfully",Toast.LENGTH_LONG).show()
                            startActivity(Intent(requireContext(),MainTabsActivity::class.java))
                            activity?.finish()
                        }
                        else{
                            Log.i("order",it.errorBody().toString())
                        }
                }

                }else{
                    showSnackBar("We can't place your order check your connection")
                }

            }
        }

        val root: View = binding.root
        return root
    }

    private fun makeOrderModel():Order_Model {
        var orderModel =OrderModel()
        orderModel.email = preference.getData(Constants.USEREMAIL)
        orderModel.shipping_address?.address1 ="${myAddress.address1 } ${myAddress.country} ${myAddress.city} ${myAddress.zip}"
        orderModel.discount_codes?.get(0)?.code =discountCode.code

        for (lineItem in line_items){
            var li = OrderModel.LineItem()
            li.quantity=lineItem.quantity
            li.variant_id =lineItem.variant_id
            myLineItems.add(li)
        }
        orderModel.line_items=myLineItems
        var order_Model = Order_Model(orderModel)
        return order_Model
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    private fun getPayment() {

        // Creating a paypal payment on below line.
        val payment = PayPalPayment(
            BigDecimal(amount), "EUR", "Order Fees",
            PayPalPayment.PAYMENT_INTENT_SALE
        )

        // Creating Paypal Payment activity intent
        val intent = Intent(activity, PaymentActivity::class.java)

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)

        // Putting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)

        // Starting the intent activity for result
        // the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    Log.i("ACTIVITYRESSULT:","")
        super.onActivityResult(requestCode, resultCode, data)
        // If the result is from paypal
        if (requestCode == PAYPAL_REQUEST_CODE) {

            // If the result is OK i.e. user has not canceled the payment
            if (resultCode == Activity.RESULT_OK) {

                // Getting the payment confirmation
                var confirm: PaymentConfirmation =
                    data!!.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION)!!;

                // if confirmation is not null
                if (confirm != null) {
                    try {
                        // Getting the payment details
                        var paymentDetails: String = confirm.toJSONObject().toString(4);
                        // on below line we are extracting json response and displaying it in a text view.
                        var payObj: JSONObject = JSONObject(paymentDetails);
                        var payID: String = payObj.getJSONObject("response").getString("id");
                        var state: String = payObj.getJSONObject("response").getString("state");
                        Log.i("PAYMENT","Payment " + state + "\n with payment id is " + payID)
                    } catch (e: JSONException) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
                        Log.i("PAYMENT","Payment error")

                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                // on below line we are checking the payment status.
                Log.i("paymentExample", "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                // on below line when the invalid paypal config is submitted.
                Log.i(
                    "paymentExample",
                    "An invalid Payment or PayPalConfiguration was submitted. Please see the docs."
                );
            }
        }
    }

    companion object {
        const val clientKey = "AVMXmO57ZhbNwUrxPpAEi7gBR6ftmNGCvDyzEXyICdGk0FPQrN9UbBuDZN--NLj78v6oBmQOAgHZ461K"
        const val PAYPAL_REQUEST_CODE = 123

        private val config = PayPalConfiguration() // Start with mock environment.  When ready,
            // switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // on below line we are passing a client id.
            .clientId(clientKey)
    }


    private fun showSnackBar(msg:String){
        val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content),
            msg, Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }

//    fun addAddress(address: Addresse){
//        myAddress = address
//        Log.i("inside communicator method",myAddress.toString())
//
//    }


}