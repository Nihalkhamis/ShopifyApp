package com.gradle.shopifyapp.model

import retrofit2.Response

interface RepositoryInterface  {
suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String) : Response<ProductModel>
suspend fun getAllVendors() : Response<VendorsModel>
suspend fun getAllProductsByBrand(id : String) : Response<ProductModel>
suspend fun getAllDiscountCodes() : Response<DiscountCodeModel>
suspend fun registerNewUser( user:CustomerModel) : Response<CustomerModel>
suspend fun getAllUsers( ) : Response<CustomersModel>
suspend fun getAllCurrencies( ) : Response<CurrencyModel>
suspend fun getAllConvertedCurrencies(amount : String, from : String, to : String) : Response<CurrencyConverterModel>


}