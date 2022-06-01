package com.gradle.shopifyapp.productBrand.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.model.RepositoryInterface

class ProductBrandViewModelFactory (private val repository: RepositoryInterface, private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductBrandViewModel::class.java)) {
            ProductBrandViewModel(repository,context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}