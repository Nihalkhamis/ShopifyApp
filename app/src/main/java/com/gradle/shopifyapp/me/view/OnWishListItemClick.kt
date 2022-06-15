package com.gradle.shopifyapp.me.view

import com.gradle.shopifyapp.draft_model.Draft_order

interface OnWishListItemClick {
    fun onClick(draftOrder:Draft_order)
}