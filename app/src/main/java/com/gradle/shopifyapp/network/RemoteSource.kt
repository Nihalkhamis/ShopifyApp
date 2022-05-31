package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts() : Response<ProductModel>
    suspend fun getAllVendors():  Response<VendorsModel>
}