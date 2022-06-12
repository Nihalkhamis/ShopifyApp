package com.gradle.shopifyapp.home.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.RepositoryInterface
import com.gradle.shopifyapp.model.VendorsModel
import kotlinx.coroutines.*

class HomeViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val productList = MutableLiveData<ProductModel>()
    val liveDataProductList : LiveData<ProductModel> = productList

    val vendorList = MutableLiveData<VendorsModel>()
    val liveVendorList : LiveData<VendorsModel> = vendorList

    val discountList = MutableLiveData<DiscountCodeModel>()
    val liveDiscountList : LiveData<DiscountCodeModel> = discountList

    fun getAllProducts(context: Context, collection_id : String, product_type : String, vendor : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllProducts(collection_id, product_type, vendor)
            productList.postValue(response.body())
        }
    }

    fun getAllVendors(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllVendors()
            vendorList.postValue(response.body())
        }
    }


    fun getAllDiscountCodes(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllDiscountCodes()
            discountList.postValue(response.body())
        }
    }

//    private fun onError(message: String) {
//        errorMessage.value = message
//        loading.value = false
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        job?.cancel()
//    }

//    val coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
//        throwable.printStackTrace()
//    }
}