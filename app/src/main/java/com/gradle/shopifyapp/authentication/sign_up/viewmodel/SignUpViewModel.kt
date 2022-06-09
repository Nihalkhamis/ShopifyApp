package com.gradle.shopifyapp.authentication.sign_up.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.RepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class SignUpViewModel (private val repo : RepositoryInterface) : ViewModel() {
    private val userResponse = MutableLiveData<Response<CustomerModel>>()
    val userResponseLiveData : LiveData<Response<CustomerModel>> = userResponse

    fun registerUser( user:CustomerModel){
        viewModelScope.launch(Dispatchers.IO ) {
           userResponse.postValue( repo.registerNewUser(user))

        }

    }

}


