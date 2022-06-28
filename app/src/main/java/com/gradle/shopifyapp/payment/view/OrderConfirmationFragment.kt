package com.gradle.shopifyapp.payment.view

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentOrderConfirmationBinding
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price
import com.gradle.shopifyapp.model.*
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModel
import com.gradle.shopifyapp.payment.viewmodel.OrderConfirmationViewModelFactory
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import org.json.JSONException
import org.json.JSONObject
import java.util.HashMap

class OrderConfirmationFragment : Fragment() {
    private var _binding: FragmentOrderConfirmationBinding? = null
    private val binding get() = _binding!!
    lateinit var preference: MyPreference

    var amount:String?=null
    lateinit var orderConfirmationVmFactory: OrderConfirmationViewModelFactory
    lateinit var orderConfirmationVm: OrderConfirmationViewModel


    lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var shoppingCartVm: ShoppingCartViewModel

    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : AlertDialog



    var line_items = ArrayList<LineItem>()
    var total_prices = ArrayList<Total_price>()
    var tax =0.0
    var totalPrice = 0.0
    var subtotal = 0.0
    var discount = 0.0
    var discountCode = DiscountCode()
    var myLineItems = mutableListOf<OrderModel.LineItem>()
    var draftOrderIds = ArrayList<Long>()


    lateinit var btnPay : Button

    val PUBLISH_KEY = "pk_test_51LC0btHd71OxeOPItO4IjE4Kezw2xz7wwAUCimJupenn5GmqczjnKviFl5nPUv8b9GcFJHdc3rTA57vo8jTC8Ti500Cpyp5Kng"
    val SECRET_KEY = "sk_test_51LC0btHd71OxeOPI32npev8ME2kKBe1GqwdOL3fFiprgQU0OrMEqbhSTH2RYCn57E0laaKUZKtME096WGGHOK2jw00ey8OaAR4"
    lateinit var paymentSheet : PaymentSheet
    lateinit var customerId: String
    lateinit var ephericalKey: String
    lateinit var clientSecret: String

    var myAddress :Addresse = Addresse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var  firstTime = true
        connectionLiveData = ConnectionLiveData(requireContext())
        dialog = Alert.makeAlert(requireContext())


        PaymentConfiguration.init(requireContext(),PUBLISH_KEY)
        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)

        val request: StringRequest = object : StringRequest(Request.Method.POST, "https://api.stripe.com/v1/customers",
            Response.Listener {response ->
                try{
                    val jsonObject = JSONObject(response)
                    customerId = jsonObject.getString("id")
                //    Toast.makeText(requireContext(),"Customer Id: "+customerId,Toast.LENGTH_SHORT).show()
                    getEphericalKey(customerId)
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String,String> {
                val header: HashMap<String,String> = HashMap<String,String>()
                header.put("Authorization" , "Bearer $SECRET_KEY")
                return header
            }
        }

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderConfirmationBinding.inflate(inflater, container, false)

        connectionLiveData.observe(viewLifecycleOwner){
            if (it){
                dialog.dismiss()

            }else{
                dialog.show()
                //showSnackBar("Be careful we lost the connection")

            }
        }
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

        line_items.clear()
        total_prices.clear()

        totalPrice =0.0
        tax = 0.0
        discount =0.0

        line_items = (requireActivity() as PaymentActivity).lineItems
        total_prices = (requireActivity() as PaymentActivity).totalPrice
        draftOrderIds = (requireActivity() as PaymentActivity).draftOrderId



        for(i in 0..total_prices.size-1){
            subtotal += (total_prices[i].subtotal!!.toDouble()* (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
            totalPrice += (total_prices[i].total!!.toDouble() * (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
            tax += (total_prices[i].tax!!.toDouble() *(preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0))
        }

        amount = totalPrice.toString()
        //////////////////////////////////
        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount+50.0))) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
        _binding!!.shipping.text = (String.format("%.2f",tax)) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
        _binding!!.subtotal.text = (String.format("%.2f",subtotal)) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
        _binding!!.shippingTxtInput.text = "50.0${preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")}"

        orderConfirmationVmFactory = OrderConfirmationViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        orderConfirmationVm = ViewModelProvider(this, orderConfirmationVmFactory).get(OrderConfirmationViewModel::class.java)


        _binding!!.verify.setOnClickListener{
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)
            orderConfirmationVm.getAllDiscountCodes(requireContext())
            orderConfirmationVm.liveDiscountList.observe(viewLifecycleOwner){discountCodes ->
                for(i in 0..discountCodes.discount_codes.size-1){
                    if(_binding!!.couponEditText.text.toString() == discountCodes.discount_codes[i].code){
                        discount = subtotal*0.1
                        _binding!!.discount.text = "-" + (String.format("%.2f",(discount))) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
                       ///////////////////////////
                        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount+50.0))) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
                        _binding!!.verify.setImageResource(R.drawable.verify_checked_icon)
                        discountCode = discountCodes.discount_codes[i]
                        break
                    }else{
                        discount = 0.0
                        _binding!!.discount.text = discount.toString() + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
                        _binding!!.verify.setImageResource(R.drawable.verify_icon)
                        /////////////////////////////////////
                        _binding!!.totalPriceTextview.text = (String.format("%.2f",(totalPrice-discount+50.0))) + preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
                    }
                }
            }
        }

        _binding!!.orderBtn.setOnClickListener {

            if(_binding!!.creditRadioBtn.isChecked )
                paymentFlow()
            if(!_binding!!.creditRadioBtn.isChecked && !_binding!!.cashRadioBtn.isChecked)
                Toast.makeText(requireContext(),"Choose a payment method!",Toast.LENGTH_LONG).show()
            if(_binding!!.cashRadioBtn.isChecked)
            {
                placeOrder("cash")

            }
        }

        val root: View = binding.root
        return root
    }

    private fun placeOrder(paymentMethod:String){
        var result_order_Model = makeOrderModel()
        result_order_Model.order?.processing_method =paymentMethod
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
            showSnackBar("We can't place your order, please check your connection")
        }
    }

    private fun makeOrderModel():Order_Model {
        var orderModel =OrderModel()
        orderModel.email = preference.getData(Constants.USEREMAIL)
        orderModel.shipping_address?.address1 ="${myAddress.address1 } ${myAddress.country} ${myAddress.city} ${myAddress.zip}"
        orderModel.discount_codes?.get(0)?.code =discount.toString()

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

    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(requireContext(), "Payment Success!!", Toast.LENGTH_SHORT).show()
            placeOrder("Stripe")
        }
    }

    private fun getEphericalKey(id: String){
        val request: StringRequest = object : StringRequest(
            Request.Method.POST, "https://api.stripe.com/v1/ephemeral_keys",
            Response.Listener { response ->
                try{
                    val jsonObject = JSONObject(response)
                    ephericalKey = jsonObject.getString("id")
                    //Toast.makeText(requireContext(),"Epherical Key: "+ephericalKey,Toast.LENGTH_SHORT).show()
                    getClientSecret(customerId,ephericalKey)
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: HashMap<String, String> = HashMap<String,String>()
                header.put("Authorization" , "Bearer $SECRET_KEY")
                header.put("Stripe-Version" , "2020-08-27")
                return header
            }

            override fun getParams(): Map<String, String> {
                val param: HashMap<String, String> = HashMap<String,String>()
                param.put("customer" ,customerId)
                return param
            }
        }

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }

    private fun getClientSecret(customerId: String, ephericalKey: String) {
        val request: StringRequest = object : StringRequest(Request.Method.POST, "https://api.stripe.com/v1/payment_intents",
            Response.Listener {response ->
                try{
                    val jsonObject = JSONObject(response)
                    clientSecret = jsonObject.getString("client_secret")
                  //  Toast.makeText(requireContext(),"Client Secret: "+ clientSecret,Toast.LENGTH_SHORT).show()
                    /// Lauch payment Flow
                    // paymentFlow()
                }catch (e : JSONException){
                    e.printStackTrace()
                }
            },
            Response.ErrorListener {
                //
            }) {
            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val header: HashMap<String,String> = HashMap<String,String>()
                header.put("Authorization" , "Bearer $SECRET_KEY")
                return header
            }

            override fun getParams(): Map<String, String> {
                val param: HashMap<String,String> = HashMap<String,String>()
                param.put("customer" ,customerId)
                val str = amount.toString()
                val delim = "."
                val list = str.split(delim)
                param.put("amount", "${list.get(0)}${list.get(1)}0")
                param.put("currency" , preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP").toString())
                param.put("automatic_payment_methods[enabled]" ,"true")
                return param
            }
        }

        val requestQueue = Volley.newRequestQueue(requireContext())
        requestQueue.add(request)
    }

    private fun paymentFlow() {
        paymentSheet.presentWithPaymentIntent(clientSecret, PaymentSheet.Configuration("SHOPIFY",
            PaymentSheet.CustomerConfiguration(customerId,ephericalKey)))
    }




}