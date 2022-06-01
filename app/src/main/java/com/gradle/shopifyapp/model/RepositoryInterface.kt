package com.gradle.shopifyapp.model

import retrofit2.Response

interface RepositoryInterface  {
suspend fun getAllProducts() : Response<ProductModel>
suspend fun getAllVendors() : Response<VendorsModel>
suspend fun getAllProductsByBrand(id : String) : Response<ProductModel>

}