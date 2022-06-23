package com.gradle.shopifyapp.productdetails.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.model.ProductItem
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductDetailsViewModel (private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val postDraftOrderList = MutableLiveData<Response<Draft_order>>()
    val liveDraftOrderList : LiveData<Response<Draft_order>> = postDraftOrderList

    private val productList = MutableLiveData<Response<ProductItem>>()
    val liveDataProductList : LiveData<Response<ProductItem>> = productList


    private val getDraftOrderList = MutableLiveData<List<DraftOrder>>()
    val liveGetDraftOrderList : LiveData<List<DraftOrder>> = getDraftOrderList

    fun postDraftOrder(order: Draft_order) {
        viewModelScope.launch {
            val response = repo.postDraftOrders(order)
            if (response.isSuccessful){
            postDraftOrderList.value = response
        }
        }
    }

    fun getProductById(productId : String){
        viewModelScope.launch {
            val response = repo.getProductById(productId)
            if (response.isSuccessful) {
                productList.value = response
            }
        }
    }


    fun getDraftOrder(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getDraftOrders()
            getDraftOrderList.postValue(response.body()?.draft_orders)
        }
    }


}