package com.gradle.shopifyapp.category.view


interface OnProductTypeItemListener {
    fun onClick(productTypeName: String, isFiltered : Boolean)
}