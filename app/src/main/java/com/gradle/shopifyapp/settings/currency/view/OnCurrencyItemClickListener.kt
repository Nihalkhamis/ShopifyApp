package com.gradle.shopifyapp.settings.currency.view

import com.gradle.shopifyapp.model.Currency

interface OnCurrencyItemClickListener {
    fun onClick(currency: Currency)
}