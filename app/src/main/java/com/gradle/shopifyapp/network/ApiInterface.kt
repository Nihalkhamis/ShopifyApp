package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiInterface {
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("products.json")
    suspend fun getAllProducts(): Response<ProductModel>

    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("smart_collections.json")
    suspend fun getAllVendors(): Response<VendorsModel>
}