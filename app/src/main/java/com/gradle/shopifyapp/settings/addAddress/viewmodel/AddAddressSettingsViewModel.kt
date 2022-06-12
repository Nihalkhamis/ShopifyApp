package com.gradle.shopifyapp.settings.addAddress.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddAddressSettingsViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
     val loading = MutableLiveData<Boolean>()
     val errorMessage = MutableLiveData<String>()


    fun addCustomerAddress(context: Context, id : String, customer: CustomerModel) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO) {

            val response = repo.addCustomerAddress(id, customer)

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