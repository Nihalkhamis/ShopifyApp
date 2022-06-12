package com.gradle.shopifyapp.payment.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentOrderConfirmationBinding
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price
import com.gradle.shopifyapp.home.viewmodel.HomeViewModel
import com.gradle.shopifyapp.home.viewmodel.HomeViewModelFactory
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModel
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModelFactory
import com.kotlin.weatherforecast.utils.Constants
import com.kotlin.weatherforecast.utils.MyPreference
import com.paypal.android.sdk.payments.*
import com.paypal.android.sdk.payments.PaymentActivity
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
    var line_items = ArrayList<LineItem>()
    var total_prices = ArrayList<Total_price>()
    var tax =0.0
    var totalPrice = 0.0
    var subtotal = 0.0
    var discount = 0.0

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


        _binding!!.backBtn.setOnClickListener {
            //findNavController(this)?.navigate(R.id.paymenttoaddress)
        }


        _binding!!.nameTextView.text = preference.getData(Constants.USERFIRSTNAME)
        _binding!!.emailTextView.text = preference.getData(Constants.USEREMAIL)
        _binding!!.phoneTextView.text = preference.getData(Constants.USERMOBILEPHONE)

        total_prices = requireArguments().getSerializable("total_prices") as ArrayList<Total_price>
        for(i in 0..total_prices.size-1){
            subtotal += total_prices[i].subtotal!!.toDouble()
            totalPrice += total_prices[i].total!!.toDouble()
            tax += total_prices[i].tax!!.toDouble()
        }
        amount = totalPrice.toString()
//        _binding!!.totalPriceTextview.text = amount +"EGP"
        _binding!!.shipping.text = tax.toString() + "EGP"
        _binding!!.subtotal.text = subtotal.toString() +"EGP"

        _binding!!.orderBtn.setOnClickListener {
            if(_binding!!.creditRadioBtn.isChecked )
                getPayment()
        }
        orderConfirmationVmFactory = OrderConfirmationViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        _binding!!.verify.setOnClickListener{
            orderConfirmationVm = ViewModelProvider(this, orderConfirmationVmFactory).get(OrderConfirmationViewModel::class.java)
            orderConfirmationVm.getAllDiscountCodes(requireContext())
            orderConfirmationVm.liveDiscountList.observe(viewLifecycleOwner){discountCodes ->
                for(i in 0..discountCodes.discount_codes.size-1){
                    if(_binding!!.couponEditText.text.toString() == discountCodes.discount_codes[i].code){
                        discount = subtotal*0.1
                        _binding!!.discount.text = "-" + (String.format("%.2f",(discount))) + "EGP"
                        _binding!!.verify.setImageResource(R.drawable.verify_checked_icon)
                        break
                    }else{
                        discount = 0.0
                        _binding!!.discount.text = discount.toString()
                        _binding!!.verify.setImageResource(R.drawable.verify_icon)
                    }
                }
            }
        }

        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount))) +"EGP"
        val root: View = binding.root
        return root
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    private fun getPayment() {

        // Creating a paypal payment on below line.
        val payment = PayPalPayment(
            BigDecimal(amount), "USD", "Course Fees",
            PayPalPayment.PAYMENT_INTENT_SALE
        )

        // Creating Paypal Payment activity intent
        val intent: Intent = Intent(activity, PaymentActivity::class.java)

        //putting the paypal configuration to the intent
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config)

        // Putting paypal payment to the intent
        intent.putExtra(PaymentActivity.EXTRA_PAYMENT, payment)

        // Starting the intent activity for result
        // the request code will be used on the method onActivityResult
        startActivityForResult(intent, PAYPAL_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
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
                    } catch (e: JSONException) {
                        // handling json exception on below line
                        Log.e("Error", "an extremely unlikely failure occurred: ", e);
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
        const val clientKey = "AbJLgQxnyBIHMlucwslWaotVaVgjSgqqFUfoNQwuUskwrL37WX68VZpHAZEcuRjq2LHT0oMcJcmKecvJ"
        const val PAYPAL_REQUEST_CODE = 123

        private val config = PayPalConfiguration() // Start with mock environment.  When ready,
            // switch to sandbox (ENVIRONMENT_SANDBOX)
            // or live (ENVIRONMENT_PRODUCTION)
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX) // on below line we are passing a client id.
            .clientId(clientKey)
    }
}