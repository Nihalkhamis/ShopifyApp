package com.gradle.shopifyapp.model

import java.io.Serializable

data class ProductModel(
     val products: List<Product>
)

data class ProductItem(
    val product: Product
)

    data class Product(
        val admin_graphql_api_id: String? = null,
        val body_html: String? = null,
        val created_at: String? = null,
        val handle: String? = null,
        val id: Long? = null,
        val image: Image? = null,
        val images: List<Image>? = null,
        val options: List<Option>? = null,
        val product_type: String? = null,
        val published_at: String? = null,
        val published_scope: String? = null,
        val status: String? = null,
        val tags: String? = null,
        val template_suffix: Any? = null,
        val title: String? = null,
        val updated_at: String? = null,
        val variants: List<Variant>? = null,
        val vendor: String? = null
    ):Serializable
   {

       data class Image(
           val admin_graphql_api_id: String,
           val alt: Any,
           val created_at: String,
           val height: Int,
           val id: Long,
           val position: Int,
           val product_id: Long,
           val src: String,
           val updated_at: String,
           val variant_ids: List<Any>,
           val width: Int
       ):Serializable

       data class Option(
           val id: Long,
           val name: String,
           val position: Int,
           val product_id: Long,
           val values: List<String>
       ):Serializable

data class Variant(
    val admin_graphql_api_id: String,
    val barcode: Any,
    val compare_at_price: Any,
    val created_at: String,
    val fulfillment_service: String,
    val grams: Int,
    val id: Long,
    val image_id: Any,
    val inventory_item_id: Long,
    val inventory_management: String,
    val inventory_policy: String,
    val inventory_quantity: Int,
    val old_inventory_quantity: Int,
    val option1: String,
    val option2: String,
    val option3: Any,
    val position: Int,
    val price: String,
    val product_id: Long,
    val requires_shipping: Boolean,
    val sku: String,
    val taxable: Boolean,
    val title: String,
    val updated_at: String,
    val weight: Double,
    val weight_unit: String
):Serializable
    }
