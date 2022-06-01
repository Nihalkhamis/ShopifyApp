package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
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

<<<<<<< Updated upstream
=======
    override suspend fun getAllVendors(): Response<VendorsModel> {
        val vendorService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return vendorService.getAllVendors()
    }

    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> {
        val discountCodeService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return discountCodeService.getAllDiscountCodes()
    }

>>>>>>> Stashed changes

}