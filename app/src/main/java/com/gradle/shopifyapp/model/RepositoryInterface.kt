package com.gradle.shopifyapp.model

import retrofit2.Response

interface RepositoryInterface  {
suspend fun getAllProducts() : Response<ProductModel>
suspend fun getAllVendors() : Response<VendorsModel>

}