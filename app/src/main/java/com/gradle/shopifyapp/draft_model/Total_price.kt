package com.gradle.shopifyapp.draft_model

import java.io.Serializable

data class Total_price(
    var subtotal:String?=null,
    var tax: String?=null,
    var total: String?=null
): Serializable
