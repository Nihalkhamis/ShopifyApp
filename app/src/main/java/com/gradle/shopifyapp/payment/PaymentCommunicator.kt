package com.gradle.shopifyapp.payment

import com.gradle.shopifyapp.model.Addresse

interface PaymentCommunicator {
    fun orderOnClickListener(address: Addresse)

}