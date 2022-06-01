package com.gradle.shopifyapp.productBrand.view

import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel

interface OnItemClickListener {
    fun onClick(productModel: Product)
}