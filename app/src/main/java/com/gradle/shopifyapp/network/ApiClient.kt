package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class ApiClient private constructor() : RemoteSource {

    companion object {
        private var instance: ApiClient? = null
        fun getClientInstance(): ApiClient? {
            return instance ?: ApiClient()
        }
    }

    object RetrofitHelper {
        val baseUrl = "https://madalex20220.myshopify.com/admin/api/2022-04/"


        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
    }

    override suspend fun getAllProducts(): Response<ProductModel> {
        val productsService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return productsService.getAllProducts()
    }

    override suspend fun getAllVendors(): Response<VendorsModel> {
        val vendorService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return vendorService.getAllVendors()
    }


}