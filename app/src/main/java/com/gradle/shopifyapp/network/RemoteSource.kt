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
    suspend fun getAllCurrencies( ) : Response<CurrencyModel>
    suspend fun getConvertedCurrency(amount : String, from : String, to : String): Response<CurrencyConverterModel>


}