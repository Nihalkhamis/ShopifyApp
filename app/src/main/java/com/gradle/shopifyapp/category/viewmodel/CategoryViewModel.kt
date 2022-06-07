package com.gradle.shopifyapp.category.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val categoriesProductList = MutableLiveData<ProductModel>()
    val liveDataCategoriesProductList : LiveData<ProductModel> = categoriesProductList

    fun getAllCategoriesProducts(context: Context, collection_id : String, product_type : String, vendor : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllProducts(collection_id, product_type, vendor)
            Log.d("TAG", "getProductsDetails: ${response.raw().request().url()}")
            Log.d("TAG", "getAllProducts: $response")
            categoriesProductList.postValue(response.body())
        }
    }
}