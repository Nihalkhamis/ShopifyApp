package com.gradle.shopifyapp.orders.orders_list.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.OrdersModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response


class OrderListViewModel(private val repo: RepositoryInterface) : ViewModel() {


    private val ordersResponse = MutableLiveData<Response<OrdersModel>>()
    val ordersResponseLiveData : LiveData<Response<OrdersModel>> = ordersResponse

    fun getOrders( id: String){
        viewModelScope.launch{
            ordersResponse.postValue( repo.getAllOrders(id))

        }

    }

}

