package com.gradle.shopifyapp.orders.order_details.view

import android.R
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.databinding.FragmentOrderDetailsBinding
import com.gradle.shopifyapp.home.view.HomeFragment
import com.gradle.shopifyapp.me.view.MeFragment
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class OrderDetailsFragment : Fragment(),ProductOnclickListener {


    private var _binding: FragmentOrderDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var productsOfTheOrder: MutableList<Product>

    lateinit var productRecyclerView:RecyclerView
    lateinit var productRecyclerViewAdapter: ProductInOrderAdapter
    lateinit var preference: MyPreference
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : AlertDialog




    var position:Int =0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        productsOfTheOrder = arrayListOf()
        position = arguments?.getInt("order_position") ?: 0
        preference = MyPreference.getInstance(requireContext())!!

        var  firstTime = true
        connectionLiveData = ConnectionLiveData(requireContext())
        dialog = Alert.makeAlert(requireContext())

        connectionLiveData.observe(viewLifecycleOwner){
            if (it){
                    dialog.dismiss()

            }else{
                dialog.show()
            }
        }
        productRecyclerView = binding.productRecyclerView
        productRecyclerViewAdapter =ProductInOrderAdapter(arrayListOf(),requireContext(),this)
        productRecyclerView.adapter=productRecyclerViewAdapter


        var order = MeFragment.ordersList[position]
        binding.orderIdText.text= "ORDER ID - ${order.id.toString()}"
        var day = order.created_at?.split("T")?.get(0)
        var time = order.created_at?.split("T")?.get(1)?.split("+")?.get(0)
        binding.orderDateText.text =  "$day, $time"
        binding.mobileNumberText.text = order.customer?.phone
        //binding.addressTextInput.text = order.shipping_address?.address1 ?: "No address for that address "
        var address =order.customer?.default_address
        binding.addressTextInput.text = address?.address1+" "+address?.city+" "+address?.country+" "+address?.zip
        var constantForPrice = preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0
        var constantForCurrency =preference.getData(Constants.TOCURRENCY)
       // (productsBrand[position].variants[0].price.toDouble() * (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0)).toString()

        binding.subTotalText.text="$constantForCurrency ${order.subtotal_price?.toDouble()?.times(constantForPrice)}"

        //binding.discountText.text= "-$constantForCurrency ${order.discount_codes?.get(0)?.code?.toDouble()?.times(constantForPrice)}"

        binding.totalPriceText.text = "$constantForCurrency ${order.total_price?.toDouble()?.times(constantForPrice)?.plus(50.0)}"
        binding.shippingFeeText.text = "$constantForCurrency 50.0"
       // binding.shippingFeeText.text = "$constantForCurrency ${(order.total_shipping_price_set?.presentment_money?.amount?.toDouble() ?: 0.0) + (order.total_shipping_price_set?.shop_money?.amount?.toDouble() ?: 0.0)}"


        //to get all the products that are exist in that order
        for(lineItem in MeFragment.ordersList[position].line_items!!){
          var product = HomeFragment.myProducts?.filter {
              it.id!!.equals(lineItem?.product_id)
          }
            productsOfTheOrder.add(product!![0])
        }
        productRecyclerViewAdapter.myProducts=productsOfTheOrder
        productRecyclerViewAdapter.notifyDataSetChanged()


        binding.backBtn.setOnClickListener {
            activity?.finish()

        }


        val root: View = binding.root
        return root
    }

    override fun orderOnClickListener(product: Product,price:String) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product",product)
        intent.putExtra("price",price)
        startActivity(intent)

    }


    private fun showSnackBar(msg:String){
        val snackBar = Snackbar.make(requireActivity().findViewById(R.id.content),
            msg, Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }




}