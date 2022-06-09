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

    private val productList = MutableLiveData<ProductModel>()
    val liveDataProductList : LiveData<ProductModel> = productList

    private val vendorList = MutableLiveData<VendorsModel>()
    val liveVendorList : LiveData<VendorsModel> = vendorList

    private val discountList = MutableLiveData<DiscountCodeModel>()
    val liveDiscountList : LiveData<DiscountCodeModel> = discountList

    // currency converter
    private val convertedCurrencyList = MutableLiveData<Double>()
    val liveDataConvertCurrencyList : LiveData<Double> = convertedCurrencyList

    fun getAllProducts(context: Context, collection_id : String, product_type : String, vendor : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllProducts(collection_id, product_type, vendor)
            Log.d("TAG", "getProductsDetails: ${response.raw().request().url()}")
            Log.d("TAG", "getAllProducts: $response")
            productList.postValue(response.body())
        }
    }

    fun getAllVendors(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllVendors()
            Log.d("TAG", "getAllVendors: ${response.raw().request().url()}")
            Log.d("TAG", "getAllVendors: ${response.body()}")
            vendorList.postValue(response.body())
        }
    }


    fun getAllDiscountCodes(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllDiscountCodes()
            Log.d("TAG", "getAllDiscountCodes: ${response.raw().request().url()}")
            Log.d("TAG", "getAllDiscountCodes: ${response.body()}")
            discountList.postValue(response.body())
        }
    }

    fun getAllConvertedCurrency(context: Context, amount : String, from : String, to : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllConvertedCurrencies(amount,from,to)
            Log.d("TAG", "get currency converter url: ${response.raw().request().url()}")
            Log.d("TAG", "getAllCurrencyResult: ${response.body()?.result}")
            convertedCurrencyList.postValue(response.body()?.result)
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