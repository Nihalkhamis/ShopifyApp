package com.gradle.shopifyapp.model

data class VendorsModel (
    val smart_collections: List<SmartCollection>
)

data class SmartCollection (
    val id: Long,
    val handle: String,
    val title: String,
    val updated_at: String,
    val body_html: String,
    val published_at: String,
    val sort_order: String,
    val template_suffix: Any? = null,
    val disjunctive: Boolean,
    val rules: List<Rule>,
    val published_scope: String,
    val admin_graphql_api_id: String,
    val image: Image
)

data class Image (
    val created_at: String,
    val alt: Any? = null,
    val width: Long,
    val height: Long,
    val src: String
)

data class Rule (
    val column: String,
    val relation: String,
    val condition: String
)

