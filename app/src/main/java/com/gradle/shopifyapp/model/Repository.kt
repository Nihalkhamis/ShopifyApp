package com.gradle.shopifyapp.model

import android.content.Context
import com.gradle.shopifyapp.network.RemoteSource
import retrofit2.Response

class Repository(var remoteSource: RemoteSource, var context: Context) : RepositoryInterface {

    companion object {
        private var instance: Repository? = null
        fun getRepoInstance(
            remoteSource: RemoteSource,
            context: Context
        ): Repository {
            return instance ?: Repository(remoteSource, context)
        }
    }

    override suspend fun getAllProducts(): Response<ProductModel> = remoteSource.getAllProducts()
    override suspend fun getAllVendors():  Response<VendorsModel> = remoteSource.getAllVendors()
    override suspend fun getAllProductsByBrand(id: String): Response<ProductModel> = remoteSource.getProductsByBrand(id)
    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> = remoteSource.getAllDiscountCodes()
    override suspend fun registerNewUser(user: CustomerModel): Response<CustomerModel>  =remoteSource.registerNewUser(user)
}