package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.sql.Struct


class ApiClient private constructor() : RemoteSource {

    companion object {
        private var instance: ApiClient? = null
        fun getClientInstance(): ApiClient? {
            return instance ?: ApiClient()
        }
    }

    object RetrofitHelper {
        //val baseUrl = "https://madalex20220.myshopify.com/admin/api/"
        val baseUrl ="https://9d169ad72dd7620e70f56b28ae6146d9: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@madalex20220.myshopify.com/admin/api/"


        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
    }

    override suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String): Response<ProductModel> {
        val productsService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return productsService.getAllProducts(collection_id, product_type, vendor)
    }

    override suspend fun getAllVendors(): Response<VendorsModel> {
        val vendorService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return vendorService.getAllVendors()
    }

    override suspend fun getProductsByBrand(id : String): Response<ProductModel> {
        val brandsProductsService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return brandsProductsService.getProductsByBrand(id)
    }

    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> {
        val discountCodeService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return discountCodeService.getAllDiscountCodes()
    }

    override suspend fun registerNewUser(user: CustomerModel): Response<CustomerModel> {
        val registerUserService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return registerUserService.registerUser(user)
    }

    override suspend fun getAllCustomers(): Response<CustomersModel> {
        val getAllUsersService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getAllUsersService.getCustomers()
    }

    override suspend fun getAllOrders( id: String): Response<OrdersModel> {
        val getAllOrdersService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getAllOrdersService.getOrders(id)
    }


}