package com.gradle.shopifyapp.shoppingCart.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class ShoppingCartViewModel (private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val getDraftOrderList = MutableLiveData<List<DraftOrder>>()
    val liveDraftOrderList : LiveData<List<DraftOrder>> = getDraftOrderList

    val updateDraftOrderList = MutableLiveData<Response<Draft_order>>()
    val liveUpdateDraftOrderList : LiveData<Response<Draft_order>> = updateDraftOrderList

    val deleteDraftOrderList = MutableLiveData<Response<Draft_order>>()
    val liveDeleteDraftOrderList : LiveData<Response<Draft_order>> = deleteDraftOrderList

    val loading = MutableLiveData<Boolean>()


    fun getDraftOrder(context: Context) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getDraftOrders()
            getDraftOrderList.postValue(response.body()?.draft_orders)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
        }
    }


    fun updateDraftOrder(id:String, order:Draft_order){
        viewModelScope.launch {
            val response = repo.updateDraftOrders(id,order)
            updateDraftOrderList.value = response
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                }
            }
        }
    }

    fun deleteProductFromDraftOrder(id:String){
        viewModelScope.launch {
            val response = repo.deleteProductFromDraftOrder(id)
            withContext(Dispatchers.Main){
                deleteDraftOrderList.value = response
            }
        }
    }


}