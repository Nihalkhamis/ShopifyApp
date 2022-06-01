package com.gradle.shopifyapp.search.views

import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel

interface OnItemClickListener {
    fun onClick(productModel: Product)
}