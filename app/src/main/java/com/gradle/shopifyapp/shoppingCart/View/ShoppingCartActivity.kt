package com.gradle.shopifyapp.shoppingCart.View;

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityShoppingCartBinding
import com.gradle.shopifyapp.payment.PaymentActivity


class ShoppingCartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShoppingCartBinding

    lateinit var shoppingCartAdapter: ShoppingCartAdapter
    lateinit var shoppingCart_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager
    var cart_items = intArrayOf(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_shopping_cart)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //shopping cart
        shoppingCart_rv = binding.shoppingCartRv
        gridLayoutManager = GridLayoutManager(applicationContext,1,GridLayoutManager.VERTICAL,false)
        shoppingCart_rv!!.layoutManager = gridLayoutManager
        shoppingCartAdapter = ShoppingCartAdapter(applicationContext,cart_items)
        shoppingCartAdapter!!.notifyDataSetChanged()
        shoppingCart_rv!!.adapter = shoppingCartAdapter

        binding.checkoutBtn.setOnClickListener{
            val intent = Intent(this, PaymentActivity::class.java)
            startActivity(intent)
        }

        binding.closeIcon.setOnClickListener{
            finish()
        }

    }




}