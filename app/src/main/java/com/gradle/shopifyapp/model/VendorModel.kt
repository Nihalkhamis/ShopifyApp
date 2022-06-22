package com.gradle.shopifyapp.model

data class VendorsModel (
    var smart_collections: List<SmartCollection>
)

data class SmartCollection (
    val id: Long?=null,
    val handle: String?=null,
    var title: String?=null,
    val updated_at: String?=null,
    val body_html: String?=null,
    val published_at: String?=null,
    val sort_order: String?=null,
    val template_suffix: Any? = null,
    val disjunctive: Boolean?=null,
    val rules: List<Rule>?=null,
    val published_scope: String?=null,
    val admin_graphql_api_id: String?=null,
    val image: Image?=null
)

data class Image (
    val created_at: String?=null,
    val alt: Any? = null,
    val width: Long?=null,
    val height: Long?=null,
    val src: String?=null
)

data class Rule (
    val column: String?=null,
    val relation: String?=null,
    val condition: String?=null
)

