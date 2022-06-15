package com.gradle.shopifyapp.network

import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.VendorsModel
import com.gradle.shopifyapp.model.*
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
        //val baseUrl = "https://madalex20220.myshopify.com/admin/api/"
        const val baseUrl ="https://9d169ad72dd7620e70f56b28ae6146d9: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@madalex20220.myshopify.com/admin/api/"
        const val currencyBaseUrl ="https://api.apilayer.com/exchangerates_data/"


        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }

        //for currency converter
        fun getCurrencyConverterInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(currencyBaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                // we need to add converter factory to
                // convert JSON object to Java object
                .build()
        }
    }

    override suspend fun getAllProducts(
        collection_id: String,
        product_type: String,
        vendor: String
    ): Response<ProductModel> {
        val productsService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return productsService.getAllProducts(collection_id, product_type, vendor)
    }

    override suspend fun getAllVendors(): Response<VendorsModel> {
        val vendorService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return vendorService.getAllVendors()
    }

    override suspend fun getProductsByBrand(id: String): Response<ProductModel> {
        val brandsProductsService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return brandsProductsService.getProductsByBrand(id)
    }

    override suspend fun getAllDiscountCodes(): Response<DiscountCodeModel> {
        val discountCodeService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return discountCodeService.getAllDiscountCodes()
    }

    override suspend fun postDraftOrders(order: Draft_order): Response<Draft_order> {
        val draftOrderService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return draftOrderService.postDraftOrders(order)
    }

    override suspend fun registerNewUser(user: CustomerModel): Response<CustomerModel> {
        val registerUserService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return registerUserService.registerUser(user)
    }

    override suspend fun getDraftOrders(): Response<Draft_orders_list> {
        val draftOrderService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return draftOrderService.getDraftOrders()
    }

    override suspend fun getAllCustomers(): Response<CustomersModel> {
        val getAllUsersService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getAllUsersService.getCustomers()
    }

    override suspend fun getAllCurrencies(): Response<CurrencyModel> {
        val getAllCurrenciesService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getAllCurrenciesService.getAllCurrencies()
    }

    override suspend fun getConvertedCurrency(
        amount: String,
        from: String,
        to: String
    ): Response<CurrencyConverterModel> {
        val getAllConvertedCurrencyService = RetrofitHelper.getCurrencyConverterInstance().create(ApiInterface::class.java)
        return getAllConvertedCurrencyService.getConvertedCurrency(amount, from, to)
    }

    override suspend fun addCustomerAddress(
        id: String,
        customer: CustomerModel
    ): Response<CustomerModel> {
        val addCustomerAddressService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return addCustomerAddressService.addCustomerAddress(id,customer)
    }

    override suspend fun getCustomerById(id: String): Response<CustomerModel> {
        val getCustomerByIdService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getCustomerByIdService.getCustomerById(id)
    }

    override suspend fun deleteAddressFromCustomer(
        customerId: String,
        addressId: String
    ): Response<CustomerModel> {
        val deleteAddressFromCustomerService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return deleteAddressFromCustomerService.deleteAddressFromCustomer(customerId, addressId)
    }

    override suspend fun getProductById(id: String): Response<ProductItem> {
        val getProductByIdService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getProductByIdService.getProductById(id)
    }


    override suspend fun getAllOrders(id: String): Response<OrdersModel> {
        val getAllOrdersService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return getAllOrdersService.getOrders(id)

    }

    override suspend fun updateDraftOrders(id: String, order: Draft_order): Response<Draft_order> {
        val draftOrderService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return draftOrderService.updateDraftOrder(id, order)
    }

    override suspend fun deleteProductFromDraftOrder(id: String): Response<Draft_order> {
        val draftOrderService = RetrofitHelper.getInstance().create(ApiInterface::class.java)
        return draftOrderService.deleteProductFromDraftOrder(id)

    }
}