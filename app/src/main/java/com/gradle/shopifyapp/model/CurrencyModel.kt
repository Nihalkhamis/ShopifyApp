package com.gradle.shopifyapp.model


import com.google.gson.annotations.SerializedName

data class CurrencyModel(
    val currencies: List<Currency>
) {
    data class Currency(
        val currency: String,
        val enabled: Boolean,
        @SerializedName("rate_updated_at")
        val rateUpdatedAt: String
    )
}