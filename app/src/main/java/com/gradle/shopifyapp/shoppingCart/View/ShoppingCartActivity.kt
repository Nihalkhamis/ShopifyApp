package com.gradle.shopifyapp.shoppingCart.View;

import android.app.AlertDialog
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityShoppingCartBinding
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.payment.view.PaymentActivity
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import com.gradle.shopifyapp.wishlist.view.WishlistActivity
import java.text.DecimalFormat


class ShoppingCartActivity : AppCompatActivity(),CartOnClickListener {

    private lateinit var binding: ActivityShoppingCartBinding

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var shoppingCart_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var preference: MyPreference
    //for internet connection
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : AlertDialog
    //viewModel
    lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var shoppingCartVm: ShoppingCartViewModel
    var products: ArrayList<Draft_order> = ArrayList<Draft_order>()
    var total_price = 0.0

    var lineItems = ArrayList< LineItem>()
    var totalPrice = ArrayList< Total_price>()
    var draftOrderId = ArrayList<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shopping_cart)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preference = MyPreference.getInstance(this)!!

        //for internet connection
        connectionLiveData = ConnectionLiveData(this)
        dialog = Alert.makeAlert(this)

        vmFactory = ShoppingCartViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )
        shoppingCartVm = ViewModelProvider(this, vmFactory).get(ShoppingCartViewModel::class.java)


        //shopping cart
        shoppingCart_rv = binding.shoppingCartRv
        gridLayoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
        shoppingCart_rv!!.layoutManager = gridLayoutManager
        shoppingCartAdapter = ShoppingCartAdapter(this,this)
        shoppingCart_rv!!.adapter = shoppingCartAdapter

        connectionLiveData.observe(this){
            if (it){
                dialog.dismiss()
                getDraftOrders()
                setUpSwipe()

                shoppingCartVm.loading.observe(this, Observer {
                    if (it) {
                        binding.progressbar.visibility = View.VISIBLE
                    } else {
                        binding.progressbar.visibility = View.GONE
                    }
                })
            }else{
                dialog.show()
            }
        }




        binding.checkoutBtn.setOnClickListener{
            if(InternetConnection.isInternetAvailable(this)){
                val intent = Intent(this, PaymentActivity::class.java)
                intent.putExtra("line_items",lineItems)
                intent.putExtra("total_prices",totalPrice)
                intent.putExtra("draft_order_id",draftOrderId)
                startActivity(intent)
            }else{
                showSnackBar()
            }

//            val intent = Intent(this, PaymentActivity::class.java)
//            intent.putExtra("line_items",lineItems)
//            intent.putExtra("total_prices",totalPrice)
//            intent.putExtra("draft_order_id",draftOrderId)
//            startActivity(intent)
        }
        binding.favoriteImg.setOnClickListener {
            startActivity(Intent(this,WishlistActivity::class.java))

        }
        binding.closeIcon.setOnClickListener{
            finish()
        }

        shoppingCartVm.loading.observe(this, Observer {
            if (it) {
                binding!!.progressbar.visibility = View.VISIBLE
            } else {
                binding!!.progressbar.visibility = View.GONE
            }
        })

    }

    private fun getDraftOrders(){
        shoppingCartVm.getDraftOrder(this)
        shoppingCartVm.liveDraftOrderList.observe(this) {

            for(i in 0..it.size-1){
                var email = preference.getData(Constants.USEREMAIL)

                if(it.get(i).email == email && it.get(i).note == "cart")
                {
                    var df: Draft_order = Draft_order()
                    df.draft_order = it.get(i)
                    products.add(df)
                    lineItems.add(df.draft_order!!.line_items!![0])
                    var totalPriceItem = Total_price()
                    totalPriceItem.subtotal = df.draft_order!!.subtotal_price
                    totalPriceItem.tax = df.draft_order!!.total_tax
                    totalPriceItem.total = df.draft_order!!.total_price
                    totalPrice.add(totalPriceItem)
                    draftOrderId.add(df.draft_order!!.id!!)
                }
            }
            if(products.isNullOrEmpty()){
                binding.backgroundImg.setImageResource(R.drawable.orders)
                binding.checkoutLayout.visibility= View.GONE
            }else{
                bindShoppingCart(products)
                calculateTotalPrice(products)
            }

        }
    }

    private fun calculateTotalPrice(draftOrder: List<Draft_order>){
        total_price = 0.0
        for(i in 0..draftOrder.size-1){
            total_price = total_price + (draftOrder.get(i).draft_order!!.line_items!![0].price!!.toDouble() * draftOrder.get(i).draft_order!!.line_items!![0].quantity!!)
            total_price = total_price  * (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0)
            Log.i("ADD", total_price.toString())
        }
        val decim = DecimalFormat("0.00")
        val price2 = decim.format(total_price).toDouble()
        binding.priceText.text = price2.toString() + (preference.getData(Constants.TOCURRENCY))
    }


    private fun bindShoppingCart(orders: ArrayList<Draft_order>){
        shoppingCartAdapter.setShoppingCart(orders)
    }

    override fun onAddProduct(draftOrder:Draft_order) {
        var updated_draft_order = draftOrder
        updated_draft_order.draft_order?.line_items!![0].quantity = draftOrder.draft_order!!.line_items!![0].quantity!!.plus(1)
        shoppingCartVm.updateDraftOrder(updated_draft_order.draft_order?.id.toString(),updated_draft_order )
        shoppingCartVm.liveUpdateDraftOrderList.observe(this) {
            calculateTotalPrice(products)
        }
    }

    override fun onRemoveProduct(draftOrder:Draft_order) {
        var updated_draft_order = draftOrder
        updated_draft_order.draft_order?.line_items!![0].quantity = draftOrder.draft_order!!.line_items!![0].quantity!!.minus(1)
        if(updated_draft_order.draft_order!!.line_items!![0].quantity!! < 1 ){
            shoppingCartVm.deleteProductFromDraftOrder(updated_draft_order.draft_order?.id.toString())
            shoppingCartVm.liveDeleteDraftOrderList.observe(this){ dOrder->
                if(dOrder.isSuccessful){
                    Log.d("TAG", "successful")
                    products.remove(draftOrder)
                    calculateTotalPrice(products)
                }
                else{
                    Log.d("TAG", "failed: ${dOrder.code()}")
                }
            }
        }else{
            shoppingCartVm.updateDraftOrder(updated_draft_order.draft_order?.id.toString(),updated_draft_order )
            shoppingCartVm.liveUpdateDraftOrderList.observe(this) {
                calculateTotalPrice(products)
            }
        }
    }

//    override fun onDeleteProduct(id: String) {
//        shoppingCartVm.deleteProductFromDraftOrder(id)
//        shoppingCartVm.liveDeleteDraftOrderList.observe(this){ dOrder->
//            if(dOrder.isSuccessful){
//                Log.d("TAG", "successful")
//                products.remove(dOrder.body())
//                calculateTotalPrice(products)
//            }
//            else{
//                Log.d("TAG", "failed: ${dOrder.code()}")
//            }
//        }
//    }

    override fun onClickProduct(draftOrder: Draft_order) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra(Constants.SELECTEDPRODUCTID, draftOrder.draft_order?.line_items?.get(0)?.product_id)
        intent.putExtra(Constants.FROMWISHLIST,"true")
        startActivity(intent)
    }

    private fun setUpSwipe() {
        val callback: ItemTouchHelper.SimpleCallback =
            object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
                //helps me to move something in recyclerView, takes 2 params the first one about the movement of the component up or down and the second one about the movement to right or left
                override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val swipedProductPosition =
                        viewHolder.adapterPosition //position of swiped item in recyclerView
                    val swipedProduct: Draft_order = shoppingCartAdapter.getProductId(swipedProductPosition)!!
                    shoppingCartVm.deleteProductFromDraftOrder(swipedProduct.draft_order!!.id.toString())
                    shoppingCartVm.liveDeleteDraftOrderList.observe(this@ShoppingCartActivity){ dOrder->
                        if(dOrder.isSuccessful){
                            Log.d("TAG", "successful")
                            //error in calculations
                            products.remove(swipedProduct)
                            shoppingCartAdapter.notifyDataSetChanged()
                            calculateTotalPrice(products)
                        }
                        else{
                            Log.d("TAG", "failed: ${dOrder.code()}")
                        }
                    }
                }
            }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(shoppingCart_rv)
    }
    private fun showSnackBar(){
        val snackBar = Snackbar.make(this.findViewById(android.R.id.content),
            "You can't check out check your connection ", Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }

}