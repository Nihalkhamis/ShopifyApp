package com.gradle.shopifyapp.productdetails.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ProductDetailsViewModel (private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val postDraftOrderList = MutableLiveData<Response<Draft_order>>()
    val liveDraftOrderList : LiveData<Response<Draft_order>> = postDraftOrderList

    fun postDraftOrder(order: Draft_order) {
        viewModelScope.launch {
            val response = repo.postDraftOrders(order)
            //Log.d("TAG", "postDraftOrder: ${response.raw().request().url()}")
          //  Log.d("TAG", "postDraftOrder: ${response.body()}")
            postDraftOrderList.value = response
        }
    }


}