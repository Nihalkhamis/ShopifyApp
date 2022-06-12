package com.gradle.shopifyapp.settings.addAddress.view

import com.gradle.shopifyapp.model.Addresse

interface OnAddressItemClickListener {
    fun onClick(address: Addresse)
}