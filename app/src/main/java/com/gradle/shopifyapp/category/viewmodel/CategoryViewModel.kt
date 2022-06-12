package com.gradle.shopifyapp.category.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CurrencyConverterModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val categoriesProductList = MutableLiveData<ProductModel>()
    val liveDataCategoriesProductList : LiveData<ProductModel> = categoriesProductList

    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCategoriesProducts(context: Context, collection_id : String, product_type : String, vendor : String) {
        loading.value = true
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllProducts(collection_id, product_type, vendor)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    loading.value = false
                } else {
                    loading.value = false
                    onError("Error : ${response.message()} ")
                }
            }
            Log.d("TAG", "getProductsDetails: ${response.raw().request().url()}")
            Log.d("TAG", "getAllProducts: $response")
            categoriesProductList.postValue(response.body())
        }
    }
    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }
}