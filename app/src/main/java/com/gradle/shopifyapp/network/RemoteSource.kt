package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import com.gradle.shopifyapp.model.*
import retrofit2.Response

interface RemoteSource {
    suspend fun getAllProducts(collection_id : String, product_type : String, vendor : String) : Response<ProductModel>
    suspend fun getAllVendors():  Response<VendorsModel>
    suspend fun getProductsByBrand(id : String):  Response<ProductModel>
    suspend fun getAllDiscountCodes():  Response<DiscountCodeModel>
    suspend fun postDraftOrders(order: Draft_order):  Response<Draft_order>
    suspend fun registerNewUser( user: CustomerModel) : Response<CustomerModel>
    suspend fun getDraftOrders(): Response<Draft_orders_list>
    suspend fun getAllCustomers( ) : Response<CustomersModel>
    suspend fun getAllOrders(  id: String) : Response<OrdersModel>
    suspend fun postOrder(  order: Order_Model) : Response<Order_Model>
    suspend fun updateDraftOrders(id: String, order: Draft_order):  Response<Draft_order>
    suspend fun deleteProductFromDraftOrder(id: String):  Response<Draft_order>


    suspend fun getAllCurrencies( ) : Response<CurrencyModel>
    suspend fun getConvertedCurrency(amount : String, from : String, to : String): Response<CurrencyConverterModel>
    suspend fun addCustomerAddress(id: String, customer: CustomerModel):  Response<CustomerModel>
    suspend fun getCustomerById(id: String) : Response<CustomerModel>
    suspend fun deleteAddressFromCustomer(customerId : String, addressId : String) : Response<CustomerModel>

}