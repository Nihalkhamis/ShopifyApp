package com.gradle.shopifyapp.shoppingCart.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ShoppingCartViewModel (private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val getDraftOrderList = MutableLiveData<List<DraftOrder>>()
    val liveDraftOrderList : LiveData<List<DraftOrder>> = getDraftOrderList

    fun getDraftOrder(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getDraftOrders()
            Log.d("TAG", "getDraftOrder: ${response.raw().request().url()}")
            Log.d("TAG", "getDraftOrder: ${response.body()}")
            getDraftOrderList.postValue(response.body()?.draft_orders)
        }
    }


}