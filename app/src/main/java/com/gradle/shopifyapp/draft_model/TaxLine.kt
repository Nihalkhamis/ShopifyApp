package com.gradle.shopifyapp.draft_model

import java.io.Serializable

data class TaxLine(
    val price: String?=null,
    val rate: Double?=null,
    val title: String?=null
): Serializable