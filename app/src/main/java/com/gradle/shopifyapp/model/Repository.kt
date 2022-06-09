package com.gradle.shopifyapp.model

import android.content.Context
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
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

    override suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String): Response<ProductModel> = remoteSource.getAllProducts(collection_id, product_type, vendor)
    override suspend fun getAllVendors():  Response<VendorsModel> = remoteSource.getAllVendors()
    override suspend fun getAllProductsByBrand(id: String): Response<ProductModel> = remoteSource.getProductsByBrand(id)
    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> = remoteSource.getAllDiscountCodes()
    override suspend fun postDraftOrders(order: Draft_order): Response<Draft_order> = remoteSource.postDraftOrders(order)
    override suspend fun registerNewUser(user: CustomerModel): Response<CustomerModel>  =remoteSource.registerNewUser(user)
    override suspend fun getDraftOrders(): Response<Draft_orders_list> = remoteSource.getDraftOrders()
    override suspend fun getAllUsers(): Response<CustomersModel> =remoteSource.getAllCustomers()
    override suspend fun getAllOrders(id: String): Response<OrdersModel> = remoteSource.getAllOrders(id)
    override suspend fun updateDraftOrders(id:String,order: Draft_order): Response<Draft_order> = remoteSource.updateDraftOrders(id,order)
    override suspend fun deleteProductFromDraftOrder(id: String): Response<Draft_order> = remoteSource.deleteProductFromDraftOrder(id)
    override suspend fun getAllCurrencies(): Response<CurrencyModel> = remoteSource.getAllCurrencies()
    override suspend fun getAllConvertedCurrencies(amount : String, from : String, to : String): Response<CurrencyConverterModel> = remoteSource.getConvertedCurrency(amount, from, to)
}