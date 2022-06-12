package com.gradle.shopifyapp.payment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OrderConfirmationViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val discountList = MutableLiveData<DiscountCodeModel>()
    val liveDiscountList : LiveData<DiscountCodeModel> = discountList

    fun getAllDiscountCodes(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllDiscountCodes()
            discountList.postValue(response.body())
        }
    }

}