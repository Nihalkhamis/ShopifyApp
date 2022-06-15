package com.gradle.shopifyapp.model

data class DiscountCode(
    val id: Long?=null,
    val price_rule_id: Long?=null,
    val code: String?=null,
    val usage_count: Int?=null,
    val created_at: String?=null,
    val updated_at: String?=null
)