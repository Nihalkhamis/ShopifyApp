package com.gradle.shopifyapp.shoppingCart.View;

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.databinding.ActivityShoppingCartBinding
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.payment.PaymentActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory


class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var shoppingCart_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    //viewModel
    lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var shoppingCartVm: ShoppingCartViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shopping_cart)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
        shoppingCartAdapter = ShoppingCartAdapter(this)
        shoppingCart_rv!!.adapter = shoppingCartAdapter
        var products: ArrayList<DraftOrder> = ArrayList<DraftOrder>()

        shoppingCartVm.getDraftOrder(this)
        shoppingCartVm.liveDraftOrderList.observe(this) {
            Log.d("TAG", "onCreateView: ${it}")
            for(i in 0..it.size-1){
                if(it.get(i).email == "shimaa226@gmail.com" && it.get(i).note == "cart")
                {
                    products.add(it.get(i))
                    Log.i("TAG","PRODUCTS")
                }

            }
            bindShoppingCart(products)
        }




        binding.checkoutBtn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        binding.closeIcon.setOnClickListener{
            finish()
        }

    }


//    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
//        return super.onCreateView(name, context, attrs)
//
//        //shopping cart
//        shoppingCart_rv = binding.shoppingCartRv
//        gridLayoutManager = GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false)
//        shoppingCart_rv!!.layoutManager = gridLayoutManager
//        shoppingCartAdapter = ShoppingCartAdapter(this)
//        shoppingCart_rv!!.adapter = shoppingCartAdapter
//
//    }

    private fun bindShoppingCart(orders: List<DraftOrder>){
        shoppingCartAdapter.setShoppingCart(orders)
    }

}