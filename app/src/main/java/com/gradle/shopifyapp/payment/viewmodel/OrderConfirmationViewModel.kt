package com.gradle.shopifyapp.payment.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.Order_Model
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class OrderConfirmationViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val discountList = MutableLiveData<DiscountCodeModel>()
    val liveDiscountList : LiveData<DiscountCodeModel> = discountList


    val orderModelResonse = MutableLiveData<Response<Order_Model>>()
    val liveOrderModel : LiveData<Response<Order_Model>> = orderModelResonse
    fun getAllDiscountCodes(context: Context) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllDiscountCodes()
            discountList.postValue(response.body())
        }
    }

    fun postOrder(orderModel:Order_Model){
        viewModelScope.launch(Dispatchers.IO ) {
            orderModelResonse.postValue(repo.postOrder(orderModel))
        }
    }

}