package com.gradle.shopifyapp.shoppingCart.View

import com.gradle.shopifyapp.draft_model.Draft_order

interface CartOnClickListener {
    fun onAddProduct(draftOrder:Draft_order)
    fun onRemoveProduct(draftOrder:Draft_order)
    fun onDeleteProduct(id:String)
    fun onClickProduct(draftOrder: Draft_order)
}