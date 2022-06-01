package com.gradle.shopifyapp.model

import retrofit2.Response

interface RepositoryInterface  {
suspend fun getAllProducts() : Response<ProductModel>
<<<<<<< Updated upstream
=======
suspend fun getAllVendors() : Response<VendorsModel>
suspend fun getAllDiscountCodes() : Response<DiscountCodeModel>

>>>>>>> Stashed changes
}