package com.gradle.shopifyapp.settings.currency.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CurrencyModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CurrencyViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val currenciesList = MutableLiveData<CurrencyModel>()
    val liveDataCurrenciesList : LiveData<CurrencyModel> = currenciesList

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCurrencies() {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllCurrencies()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
            Log.d("TAG", "getCurrencies URL: ${response.raw().request().url()}")
            Log.d("TAG", "getAllCurrencies: ${response.body()}")
            currenciesList.postValue(response.body())
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}