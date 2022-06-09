package com.gradle.shopifyapp.authentication.login.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CustomersModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val repo: RepositoryInterface) :
    ViewModel() {
    private val customersResponse = MutableLiveData<Response<CustomersModel>>()
    val customersResponseLiveData: LiveData<Response<CustomersModel>> = customersResponse

    fun getAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
           customersResponse.postValue(repo.getAllUsers())
        }
    }


}






