package com.gradle.shopifyapp.settings.ShowAllAddresses.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsAddressViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val addressesList = MutableLiveData<CustomerModel>()
    val liveDataAddressesList : LiveData<CustomerModel> = addressesList

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun getAllAddresses(context: Context, customerId : String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getCustomerById(customerId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
            Log.d("TAG", "getCustomerURL: ${response.raw().request().url()}")
            Log.d("TAG", "getAllAddresses: ${response.body()!!.customer!!.addresses}")
            addressesList.postValue(response.body())
        }
    }

    fun deleteAddress(context: Context, customerId: String, addressId : String){
        loading.value = true
        viewModelScope.launch(Dispatchers.IO){
            val response = repo.deleteAddressFromCustomer(customerId, addressId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}