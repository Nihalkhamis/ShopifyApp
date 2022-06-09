package com.gradle.shopifyapp.authentication.login.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.authentication.login.viewmodel.LoginViewModel
import com.gradle.shopifyapp.authentication.login.viewmodel.LoginViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentLoginBinding
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var preference: MyPreference

    //ViewModel
    lateinit var vmFactory: LoginViewModelFactory
    lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        preference = MyPreference.getInstance(requireContext())!!



        //ViewModel
        vmFactory = LoginViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        loginViewModel = ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)



        binding.cancelIc.setOnClickListener {
            findNavController(this)?.popBackStack()

        }

        binding.loginBtn.setOnClickListener {
            if (emailEditText.text.toString()!=""&&passwordEditText.text.toString()!=""){
                loginViewModel.getAllUsers()
                binding.progressbar.visibility = View.VISIBLE
                loginViewModel.customersResponseLiveData.observe(viewLifecycleOwner){
                    if(it.isSuccessful){
                        var check =false
                        for (user in it.body()?.customers!!){
                            check = true
                            if (user.email==emailEditText.text.toString().trim()&&user.tags==passwordEditText.text.toString()){
                                preference.saveData(Constants.USERID, user.id.toString())
                                preference.saveData(Constants.USEREMAIL, user.email.toString())
                                preference.saveData(Constants.USERFIRSTNAME, user.first_name.toString())
                                binding.progressbar.visibility = View.GONE
                                startActivity(Intent(requireContext(), MainTabsActivity::class.java))
                                break
                            }

                        }
                        if (!check){
                            Toast.makeText(requireContext(), "No user exist with that data", Toast.LENGTH_LONG).show()
                        }
                        binding.progressbar.visibility = View.GONE

                    }
                    else{
                        Toast.makeText(requireContext(), "Error Connection", Toast.LENGTH_LONG).show()

                    }

                }
            }else{
                Toast.makeText(requireContext(), "Please Complete Your Fields At first", Toast.LENGTH_LONG).show()

            }


        }

        emailEditText = binding.emailEdt
        passwordEditText = binding.passwordEdt


        val root: View = binding.root
        return root
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }


}