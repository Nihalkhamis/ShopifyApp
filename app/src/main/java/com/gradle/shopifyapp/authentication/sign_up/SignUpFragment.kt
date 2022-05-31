package com.gradle.shopifyapp.authentication.sign_up

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.databinding.FragmentSignUpBinding
import com.gradle.shopifyapp.model.Addresse
import com.gradle.shopifyapp.model.Customer
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    lateinit var backBtn:ImageView
    lateinit var emailTxt:EditText
    lateinit var passwordTxt:EditText
    lateinit var confirmPasswordTxt:EditText
    lateinit var firstName:EditText
    lateinit var lastName:EditText
    lateinit var phoneTxt:EditText
    lateinit var signUpBtn:Button
    var rresponse:MutableLiveData<CustomerModel> = MutableLiveData()
     var myCustomer :CustomerModel = CustomerModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        backBtn = binding.cancelIc
        backBtn.setOnClickListener {
            findNavController(this)?.popBackStack()

        }

        emailTxt = binding.emailEdt
        passwordTxt = binding.passwordEdt
        confirmPasswordTxt = binding.confirmPasswordEdt
        firstName = binding.firstNameEdt
        lastName = binding.secondNameEdt
        phoneTxt = binding.phoneEdt
        var c = Customer()
        c.first_name = firstName.text.toString().trim()
        c.last_name =lastName.text.toString().trim()
        c.email = emailTxt.text.toString().trim()
        c.phone=  phoneTxt.text.toString().trim()
       // c.password =passwordTxt.text.toString().trim()
       // c.password_confirmation =confirmPasswordTxt.text.toString().trim()

        c.verified_email = true
        c.addresses = listOf(Addresse(address1 ="123 Oak St",city =  "Ottawa",province = "ON",zip = "123 ABC",last_name ="Lastnameson",first_name = "Mother",company = "CA" ))
       // c.send_email_welcome = false;
        myCustomer.customer=c

        signUpBtn = binding.signupBtn
        
        signUpBtn.setOnClickListener {

//
//            //https://9d169ad72dd7620e70f56b28ae6146d9:shpat_e9319cd850d37f28a5cf73b6d1
//            //3bd985@madalex20220.myshopify.com/admin/api/2022-01/collects.json
//
//
//
//
//            val productsService = ApiClient.RetrofitHelper.getInstance().create(ApiInterface::class.java)
//            CoroutineScope(Dispatchers.IO).launch {
//                productsService.registerUser(c)
//              //  rresponse.postValue(productsService.registerUser(c))
//                //Log.i("Tag",rresponse.toString())
//            }

        }
        val root: View = binding.root
        return root
    }



    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }
}