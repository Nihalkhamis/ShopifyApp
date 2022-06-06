package com.gradle.shopifyapp.model

import com.gradle.shopifyapp.draft_model.Draft_order
import retrofit2.Response

interface RepositoryInterface  {
suspend fun getAllProducts() : Response<ProductModel>
suspend fun getAllVendors() : Response<VendorsModel>
suspend fun getAllProductsByBrand(id : String) : Response<ProductModel>
suspend fun getAllDiscountCodes() : Response<DiscountCodeModel>
suspend fun postDraftOrders(order: Draft_order):  Response<Draft_order>



}