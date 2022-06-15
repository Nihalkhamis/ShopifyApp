package com.gradle.shopifyapp.payment.view

import com.gradle.shopifyapp.model.Addresse

interface PaymentCommunicator {
    fun orderOnClickListener(address: Addresse)

}