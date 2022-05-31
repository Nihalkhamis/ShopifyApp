package com.gradle.shopifyapp.model

data class DefaultAddress(
    var address1: String = "",
    var address2: String = "",
    var city: String="",
    var company: String ="",
    var country: String="",
    var country_code: String="",
    var country_name: String="",
    var customer_id: Int =0,
    var default: Boolean =false,
    var first_name: String="",
    var id: Int=0,
    var last_name: String ="",
    var name: String="",
    var phone: String="",
    var province: String = "ON",
    var province_code: String="",
    var zip: String = "11693"
)