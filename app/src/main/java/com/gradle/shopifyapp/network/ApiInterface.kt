package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.*
import  com.gradle.shopifyapp.model.*
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Query

interface ApiInterface {
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-04/products.json")
    suspend fun getAllProducts(
        @Query("collection_id") collection_id : String,
        @Query("product_type") product_type : String,
        @Query("vendor") vendor : String,
    ): Response<ProductModel>


    @Headers(
        "X-Shopify-Shop-Api-Call-Limit: 40/40",
        "Retry-After: 2.0",
        "Accept: application/json",
        "X-Shopify-Access-Token: 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"
    )
    @POST("2022-04/customers.json")
    suspend fun registerUser(
        @Body customer: CustomerModel
    ): Response<CustomerModel>


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


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-01/price_rules/1089622311051/discount_codes.json")
    suspend fun getAllDiscountCodes(): Response<DiscountCodeModel>


    @Headers(
        "X-Shopify-Shop-Api-Call-Limit: 40/40",
        "Retry-After: 2.0",
        "Accept: application/json",
        "X-Shopify-Access-Token: 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"
    )
    @GET("2022-04/customers.json")
    suspend fun getCustomers(): Response<CustomersModel>

    //get customer by id
    @Headers(
        "X-Shopify-Shop-Api-Call-Limit: 40/40",
        "Retry-After: 2.0",
        "Accept: application/json",
        "X-Shopify-Access-Token: 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"
    )
    @GET("2022-04/customers/{customer_id}.json")
    suspend fun getCustomerById(
        @Path(value = "customer_id", encoded = false) key: String,
    ): Response<CustomerModel>


    @Headers(
        "X-Shopify-Shop-Api-Call-Limit: 40/40",
        "Retry-After: 2.0",
        "Accept: application/json",
        "X-Shopify-Access-Token: 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"
    )
    @GET("2022-04/customers/{customer_id}/orders.json")
    suspend fun getOrders(
        @Path(value = "customer_id", encoded = false) key: String,
    ): Response<OrdersModel>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @POST("2022-01/draft_orders.json")
    suspend fun postDraftOrders(@Body order:Draft_order): Response<Draft_order>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-01/draft_orders.json")
    suspend fun getDraftOrders(): Response<Draft_orders_list>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @PUT("2022-01/draft_orders/{id}.json")
    suspend fun updateDraftOrder(@Path("id")id: String,@Body order:Draft_order): Response<Draft_order>


    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @DELETE("2022-01/draft_orders/{id}.json")
    suspend fun deleteProductFromDraftOrder(@Path("id") id: String?): Response<Draft_order>


    //delete address from user
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @DELETE("2022-04/customers/{customer_id}/addresses/{address_id}.json")
    suspend fun deleteAddressFromCustomer(@Path("customer_id") customerId: String?,@Path("address_id") addressId : String?): Response<CustomerModel>


    // get all currencies
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @GET("2022-04/currencies.json")
    suspend fun getAllCurrencies(): Response<CurrencyModel>


    // currency converter
    @GET("convert?apikey=xhRKCA6ZS9CBhQ7BTEsSXDaJMo6Z9X5N")
    suspend fun getConvertedCurrency(
        @Query("amount") amount: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): Response<CurrencyConverterModel>


    //put address to user
    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @PUT("2022-04/customers/"+"{id}"+".json")
    suspend fun addCustomerAddress(@Path("id") id: String? , @Body customer: CustomerModel): Response<CustomerModel>




    @Headers(
        "Accept: application/json",
        "X-Shopify-Access-Token: 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",
    )
    @POST("2022-01/orders.json")
    suspend fun postOrders(@Body order:Order_Model): Response<Order_Model>
}