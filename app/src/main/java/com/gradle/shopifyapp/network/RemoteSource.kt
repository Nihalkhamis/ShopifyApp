package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts() : Response<ProductModel>
<<<<<<< Updated upstream
=======
    suspend fun getAllVendors():  Response<VendorsModel>
    suspend fun getAllDiscountCodes():  Response<DiscountCodeModel>

>>>>>>> Stashed changes
}