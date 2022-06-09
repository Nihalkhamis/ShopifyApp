package com.gradle.shopifyapp.shoppingCart.View;

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.databinding.ActivityShoppingCartBinding
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.payment.PaymentActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.kotlin.weatherforecast.utils.Constants
import com.kotlin.weatherforecast.utils.MyPreference


class ShoppingCartActivity : AppCompatActivity(),CartOnClickListener {

    private lateinit var binding: ActivityShoppingCartBinding

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var shoppingCart_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var preference: MyPreference

    //viewModel
    lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var shoppingCartVm: ShoppingCartViewModel
    var products: ArrayList<Draft_order> = ArrayList<Draft_order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shopping_cart)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preference = MyPreference.getInstance(this)!!

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

        getDraftOrders()
        setUpSwipe()

        binding.checkoutBtn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        binding.closeIcon.setOnClickListener{
            finish()
        }

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
                }
            }

            bindShoppingCart(products)
            calculateTotalPrice(products)
        }
    }

    private fun calculateTotalPrice(draftOrder: List<Draft_order>){
        var total_price = 0.0
        for(i in 0..draftOrder.size-1){
            total_price = total_price + (draftOrder.get(i).draft_order!!.line_items!![0].price!!.toDouble() * draftOrder.get(i).draft_order!!.line_items!![0].quantity!!)
            Log.i("ADD", total_price.toString())
        }
        binding.priceText.text = total_price.toString()
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

    override fun onDeleteProduct(id: String) {
        shoppingCartVm.deleteProductFromDraftOrder(id)
        shoppingCartVm.liveDeleteDraftOrderList.observe(this){ dOrder->
            if(dOrder.isSuccessful){
                Log.d("TAG", "successful")
            }
            else{
                Log.d("TAG", "failed: ${dOrder.code()}")
            }
        }
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

}