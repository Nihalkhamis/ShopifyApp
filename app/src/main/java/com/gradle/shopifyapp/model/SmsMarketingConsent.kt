package com.gradle.shopifyapp.model

data class SmsMarketingConsent(
    var consent_collected_from: String="",
    var consent_updated_at: Any="",
    var opt_in_level: String="",
    var state: String=""
)