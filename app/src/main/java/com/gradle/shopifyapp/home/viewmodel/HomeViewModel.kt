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

    val loading = MutableLiveData<Boolean>()


    // currency converter
    private val convertedCurrencyList = MutableLiveData<Double>()
    val liveDataConvertCurrencyList : LiveData<Double> = convertedCurrencyList

    fun getAllProducts(context: Context, collection_id : String, product_type : String, vendor : String) {
        viewModelScope.launch {
            val response = repo.getAllProducts(collection_id, product_type, vendor)
            productList.postValue(response.body())
        }
    }

    fun getAllVendors(context: Context) {
        viewModelScope.launch {
            val response = repo.getAllVendors()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    vendorList.postValue(response.body())
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
        }
    }


    fun getAllDiscountCodes(context: Context) {
        viewModelScope.launch{
            val response = repo.getAllDiscountCodes()
            discountList.postValue(response.body())
        }
    }

    fun getAllConvertedCurrency(context: Context, amount : String, from : String, to : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllConvertedCurrencies(amount,from,to)
            convertedCurrencyList.postValue(response.body()?.result)
        }
    }

}