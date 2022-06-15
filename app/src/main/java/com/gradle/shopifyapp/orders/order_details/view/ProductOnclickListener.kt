package com.gradle.shopifyapp.orders.order_details.view

import com.gradle.shopifyapp.model.Product

interface ProductOnclickListener {
    fun orderOnClickListener(product:Product,price:String)

}