package com.gradle.shopifyapp.payment.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.draft_model.Total_price
import com.gradle.shopifyapp.model.Addresse

class PaymentActivity : AppCompatActivity() , PaymentCommunicator {
    lateinit var orderConfirmationFragment:OrderConfirmationFragment
    var lineItems = ArrayList<LineItem>()
    var totalPrice = ArrayList<Total_price>()
    var myAddress:Addresse= Addresse()
    lateinit var draftId :String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        lineItems = intent.getSerializableExtra("line_items") as ArrayList<LineItem>
        totalPrice = intent.getSerializableExtra("total_prices") as ArrayList<Total_price>
        orderConfirmationFragment = OrderConfirmationFragment()
    }

    override fun orderOnClickListener(address: Addresse) {
        myAddress= address
    }

}