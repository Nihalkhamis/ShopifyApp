package com.gradle.shopifyapp.settings.currency.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CurrencyModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CurrencyViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val currenciesList = MutableLiveData<CurrencyModel>()
    val liveDataCurrenciesList : LiveData<CurrencyModel> = currenciesList

    fun getAllCurrencies() {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllCurrencies()
            Log.d("TAG", "getCurrencies URL: ${response.raw().request().url()}")
            Log.d("TAG", "getAllCurrencies: $response")
            currenciesList.postValue(response.body())
        }
    }
}