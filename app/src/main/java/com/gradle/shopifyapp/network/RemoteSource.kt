package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String) : Response<ProductModel>
    suspend fun getAllVendors():  Response<VendorsModel>
    suspend fun getProductsByBrand(id : String):  Response<ProductModel>
    suspend fun getAllDiscountCodes():  Response<DiscountCodeModel>
    suspend fun postDraftOrders(order: Draft_order):  Response<Draft_order>
    suspend fun registerNewUser( user: CustomerModel) : Response<CustomerModel>
    suspend fun getDraftOrders(): Response<Draft_orders_list>



}