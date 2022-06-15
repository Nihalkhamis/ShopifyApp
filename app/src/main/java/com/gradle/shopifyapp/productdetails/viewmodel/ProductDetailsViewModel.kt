package com.gradle.shopifyapp.productdetails.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.model.ProductItem
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductDetailsViewModel (private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val postDraftOrderList = MutableLiveData<Response<Draft_order>>()
    val liveDraftOrderList : LiveData<Response<Draft_order>> = postDraftOrderList

    private val productList = MutableLiveData<Response<ProductItem>>()
    val liveDataProductList : LiveData<Response<ProductItem>> = productList



    fun postDraftOrder(order: Draft_order) {
        viewModelScope.launch {
            val response = repo.postDraftOrders(order)
            postDraftOrderList.value = response
        }
    }

    fun getProductById(productId : String){
        viewModelScope.launch {
            val response = repo.getProductById(productId)
            productList.value = response
        }
    }





}