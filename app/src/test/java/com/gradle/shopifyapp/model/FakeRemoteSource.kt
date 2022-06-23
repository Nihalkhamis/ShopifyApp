package com.gradle.shopifyapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.network.RemoteSource
import retrofit2.Response

class FakeRemoteSource: RemoteSource {
    override suspend fun getAllProducts(
        collection_id: String,
        product_type: String,
        vendor: String
    ): Response<ProductModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllVendors(): Response<VendorsModel> {
        var brandItems: MutableList<SmartCollection> = mutableListOf()
        brandItems.add(SmartCollection(title = "Adidas"))
        brandItems.add(SmartCollection(title = "Reebok"))
        return Response.success(200, VendorsModel(brandItems))
    }

    override suspend fun getProductsByBrand(id: String): Response<ProductModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> {
        TODO("Not yet implemented")
    }

    override suspend fun postDraftOrders(order: Draft_order): Response<Draft_order> {
        TODO("Not yet implemented")
    }

    override suspend fun registerNewUser(user: CustomerModel): Response<CustomerModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getDraftOrders(): Response<Draft_orders_list> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCustomers(): Response<CustomersModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllOrders(id: String): Response<OrdersModel> {
        TODO("Not yet implemented")
    }

    override suspend fun postOrder(order: Order_Model): Response<Order_Model> {
        TODO("Not yet implemented")
    }

    override suspend fun updateDraftOrders(id: String, order: Draft_order): Response<Draft_order> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProductFromDraftOrder(id: String): Response<Draft_order> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllCurrencies(): Response<CurrencyModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getConvertedCurrency(
        amount: String,
        from: String,
        to: String
    ): Response<CurrencyConverterModel> {
        TODO("Not yet implemented")
    }

    override suspend fun addCustomerAddress(
        id: String,
        customer: CustomerModel
    ): Response<CustomerModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getCustomerById(id: String): Response<CustomerModel> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAddressFromCustomer(
        customerId: String,
        addressId: String
    ): Response<CustomerModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getProductById(id: String): Response<ProductItem> {
        TODO("Not yet implemented")
    }

}