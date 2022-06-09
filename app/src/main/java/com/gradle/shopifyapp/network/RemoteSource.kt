package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.*
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String) : Response<ProductModel>
    suspend fun getAllVendors():  Response<VendorsModel>
    suspend fun getProductsByBrand(id : String):  Response<ProductModel>
    suspend fun getAllDiscountCodes():  Response<DiscountCodeModel>
    suspend fun registerNewUser( user: CustomerModel) : Response<CustomerModel>
    suspend fun getAllCustomers( ) : Response<CustomersModel>
    suspend fun getAllOrders(  id: String) : Response<OrdersModel>


}