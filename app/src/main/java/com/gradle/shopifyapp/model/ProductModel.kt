package com.gradle.shopifyapp.model


import com.google.gson.annotations.SerializedName

data class ProductModel(
   // val product: Product
     val products: List<Product>

)
//{
//    data class Product(
//        @SerializedName("admin_graphql_api_id")
//        val adminGraphqlApiId: String,
//        @SerializedName("body_html")
//        val bodyHtml: String,
//        @SerializedName("created_at")
//        val createdAt: String,
//        val handle: String,
//        val id: Long,
//        val image: Image,
//        val images: List<Image>,
//        val options: List<Option>,
//        @SerializedName("product_type")
//        val productType: String,
//        @SerializedName("published_at")
//        val publishedAt: String,
//        @SerializedName("published_scope")
//        val publishedScope: String,
//        val status: String,
//        val tags: String,
//        @SerializedName("template_suffix")
//        val templateSuffix: Any?,
//        val title: String,
//        @SerializedName("updated_at")
//        val updatedAt: String,
//        val variants: List<Variant>,
//        val vendor: String
//    )

    data class Product(
        val admin_graphql_api_id: String,
        val body_html: String,
        val created_at: String,
        val handle: String,
        val id: Long,
        val image: Image,
        val images: List<Image>,
        val options: List<Option>,
        val product_type: String,
        val published_at: String,
        val published_scope: String,
        val status: String,
        val tags: String,
        val template_suffix: Any,
        val title: String,
        val updated_at: String,
        val variants: List<Variant>,
        val vendor: String
    )
   {
//        data class Image(
//            @SerializedName("admin_graphql_api_id")
//            val adminGraphqlApiId: String,
//            val alt: Any?,
//            @SerializedName("created_at")
//            val createdAt: String,
//            val height: Int,
//            val id: Long,
//            val position: Int,
//            @SerializedName("product_id")
//            val productId: Long,
//            val src: String,
//            @SerializedName("updated_at")
//            val updatedAt: String,
//            @SerializedName("variant_ids")
//            val variantIds: List<Any>,
//            val width: Int
//        )

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
       )

//        data class Option(
//            val id: Long,
//            val name: String,
//            val position: Int,
//            @SerializedName("product_id")
//            val productId: Long,
//            val values: List<String>
//        )

       data class Option(
           val id: Long,
           val name: String,
           val position: Int,
           val product_id: Long,
           val values: List<String>
       )

//        data class Variant(
//            @SerializedName("admin_graphql_api_id")
//            val adminGraphqlApiId: String,
//            val barcode: Any?,
//            @SerializedName("compare_at_price")
//            val compareAtPrice: Any?,
//            @SerializedName("created_at")
//            val createdAt: String,
//            @SerializedName("fulfillment_service")
//            val fulfillmentService: String,
//            val grams: Int,
//            val id: Long,
//            @SerializedName("image_id")
//            val imageId: Any?,
//            @SerializedName("inventory_item_id")
//            val inventoryItemId: Long,
//            @SerializedName("inventory_management")
//            val inventoryManagement: String,
//            @SerializedName("inventory_policy")
//            val inventoryPolicy: String,
//            @SerializedName("inventory_quantity")
//            val inventoryQuantity: Int,
//            @SerializedName("old_inventory_quantity")
//            val oldInventoryQuantity: Int,
//            val option1: String,
//            val option2: String,
//            val option3: Any?,
//            val position: Int,
//            val price: String,
//            @SerializedName("product_id")
//            val productId: Long,
//            @SerializedName("requires_shipping")
//            val requiresShipping: Boolean,
//            val sku: String,
//            val taxable: Boolean,
//            val title: String,
//            @SerializedName("updated_at")
//            val updatedAt: String,
//            val weight: Double,
//            @SerializedName("weight_unit")
//            val weightUnit: String
//        )
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
)
    }
//}