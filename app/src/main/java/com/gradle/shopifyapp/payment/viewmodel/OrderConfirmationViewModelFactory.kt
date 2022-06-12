package com.gradle.shopifyapp.payment.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.model.RepositoryInterface
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel

class OrderConfirmationViewModelFactory (private val repository: RepositoryInterface, private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrderConfirmationViewModel::class.java)) {
            OrderConfirmationViewModel(repository,context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}