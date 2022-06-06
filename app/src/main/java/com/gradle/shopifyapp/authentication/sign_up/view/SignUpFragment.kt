package com.gradle.shopifyapp.authentication.sign_up.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.authentication.sign_up.viewmodel.SignUpViewModel
import com.gradle.shopifyapp.authentication.sign_up.viewmodel.SignUpViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentSignUpBinding
import com.gradle.shopifyapp.model.Customer
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.kotlin.weatherforecast.utils.Constants
import com.kotlin.weatherforecast.utils.MyPreference


class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    lateinit var backBtn: ImageView
    lateinit var emailTxt: EditText
    lateinit var passwordTxt: EditText
    lateinit var confirmPasswordTxt: EditText
    lateinit var firstName: EditText
    lateinit var lastName: EditText
    lateinit var phoneTxt: EditText
    var myCustomer: CustomerModel = CustomerModel()


    lateinit var preference: MyPreference


    lateinit var vmFactory: SignUpViewModelFactory
    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        backBtn = binding.cancelIc
        backBtn.setOnClickListener {
            findNavController(this)?.popBackStack()

        }
        preference = MyPreference.getInstance(requireContext())!!




        vmFactory = SignUpViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        signUpViewModel = ViewModelProvider(this, vmFactory).get(SignUpViewModel::class.java)


        emailTxt = binding.emailEdt
        passwordTxt = binding.passwordEdt
        confirmPasswordTxt = binding.confirmPasswordEdt
        firstName = binding.firstNameEdt
        lastName = binding.secondNameEdt
        phoneTxt = binding.phoneEdt
        var c = Customer()






        binding.signupBtn.setOnClickListener {

            c.first_name = firstName.text.toString().trim()
            c.last_name = lastName.text.toString().trim()
            c.email = emailTxt.text.toString().trim()
            c.phone = phoneTxt.text.toString().trim()
            c.password = passwordTxt.text.toString().trim()
            c.password_confirmation = confirmPasswordTxt.text.toString().trim()
            c.verified_email = true
            myCustomer.customer = c
            signUpViewModel.registerUser(myCustomer)
            binding.progressbar.visibility = View.VISIBLE

            signUpViewModel.userResponseLiveData.observe(viewLifecycleOwner) {
                if (it.isSuccessful) {
                    startActivity(Intent(requireContext(), MainTabsActivity::class.java))
                    preference.saveData(Constants.USERID, it.body()?.customer?.id.toString())

                    binding.progressbar.visibility = View.GONE
                } else {
                    binding.progressbar.visibility = View.GONE
                    Log.i("error", it.code().toString())
                    Toast.makeText(requireContext(), it.message(), Toast.LENGTH_LONG).show()

                }

            }
//
//            //https://9d169ad72dd7620e70f56b28ae6146d9:shpat_e9319cd850d37f28a5cf73b6d1
//            //3bd985@madalex20220.myshopify.com/admin/api/2022-01/collects.json
//
//


        }
        val root: View = binding.root
        return root
    }


    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }


}