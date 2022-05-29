package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.ProductModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts() : Response<ProductModel>
}