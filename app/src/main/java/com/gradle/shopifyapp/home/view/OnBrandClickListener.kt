package com.gradle.shopifyapp.home.view

import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SmartCollection
import com.gradle.shopifyapp.model.SubCategoryModel

interface OnBrandClickListener {
    fun onClick(smartCollection: SmartCollection)
}