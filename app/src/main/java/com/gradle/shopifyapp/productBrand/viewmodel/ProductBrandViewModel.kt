package com.gradle.shopifyapp.productBrand.viewmodel

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

class ProductBrandViewModel(private val repo : RepositoryInterface, private var context: Context) : ViewModel() {

    private val brandsProductList = MutableLiveData<ProductModel>()
    val liveDataBrandsProductList : LiveData<ProductModel> = brandsProductList

    fun getAllBrandsProducts(context: Context, id : String) {
        viewModelScope.launch(Dispatchers.IO ) {
            val response = repo.getAllProductsByBrand(id)
            Log.d("TAG", "getProductsDetails: ${response.raw().request().url()}")
            Log.d("TAG", "getAllProducts: $response")
            brandsProductList.postValue(response.body())
        }
    }
}