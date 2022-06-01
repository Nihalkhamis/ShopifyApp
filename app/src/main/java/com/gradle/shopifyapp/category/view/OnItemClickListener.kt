package com.gradle.shopifyapp.category.view

import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel

interface OnItemClickListener {
    fun onClick(subCategoryModel: SubCategoryModel)
}