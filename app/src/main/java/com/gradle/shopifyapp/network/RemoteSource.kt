package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts() : Response<ProductModel>
    suspend fun getAllVendors():  Response<VendorsModel>
    suspend fun getProductsByBrand(id : String):  Response<ProductModel>
    suspend fun getAllDiscountCodes():  Response<DiscountCodeModel>
    suspend fun registerNewUser( user: CustomerModel) : Response<CustomerModel>


}