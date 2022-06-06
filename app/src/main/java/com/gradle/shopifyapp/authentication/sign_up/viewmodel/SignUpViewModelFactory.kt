package com.gradle.shopifyapp.authentication.sign_up.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.model.RepositoryInterface

class SignUpViewModelFactory(private val repository: RepositoryInterface, private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SignUpViewModel::class.java)) {
            SignUpViewModel(repository,context) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }    }
}



