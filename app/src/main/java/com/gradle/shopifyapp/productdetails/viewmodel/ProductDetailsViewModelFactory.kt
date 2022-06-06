package com.gradle.shopifyapp.productdetails.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.model.RepositoryInterface

class ProductDetailsViewModelFactory (private val repository: RepositoryInterface, private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(ProductDetailsViewModel::class.java)) {
            ProductDetailsViewModel(repository,context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}