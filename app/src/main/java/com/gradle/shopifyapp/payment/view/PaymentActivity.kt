package com.gradle.shopifyapp.payment.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.address.view.AddressFragment
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price

class PaymentActivity : AppCompatActivity() {
    var lineItems = ArrayList<LineItem>()
    var totalPrice = ArrayList<Total_price>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        lineItems = intent.getSerializableExtra("line_items") as ArrayList<LineItem>
        totalPrice = intent.getSerializableExtra("total_prices") as ArrayList<Total_price>

        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        var bundle = Bundle()
        bundle.putSerializable("line_items",lineItems)
        bundle.putSerializable("total_prices",totalPrice)
        val mFragment = AddressFragment()
        mFragment.arguments = bundle
        mFragmentTransaction.replace(R.id.payment_nav,mFragment).commit()
    //        mFragmentTransaction.add(R.id.payment_nav, mFragment).commit()

    }


}