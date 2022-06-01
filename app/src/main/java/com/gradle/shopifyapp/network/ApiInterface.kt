package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.Customer
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-04/products.json")
    suspend fun getAllProducts(): Response<ProductModel>



//    @Headers(
//        "Accept: application/json",
//        "X-Shopify-Access-Token: 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",
//    )
    @POST("2022-04/customers.json")
    suspend fun registerUser(customer: Customer):CustomerModel


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-04/smart_collections.json")
    suspend fun getAllVendors(): Response<VendorsModel>


    //Products by brand
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-01/collections/{id}/products.json")
    suspend fun getProductsByBrand(
        @Path(value = "id", encoded = false) key: String,
    ): Response<ProductModel>



}