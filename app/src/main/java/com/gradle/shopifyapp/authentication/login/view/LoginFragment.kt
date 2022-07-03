package com.gradle.shopifyapp.authentication.login.view

import android.R
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.authentication.login.viewmodel.LoginViewModel
import com.gradle.shopifyapp.authentication.login.viewmodel.LoginViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentLoginBinding
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class LoginFragment : Fragment() {
    lateinit var connectionLiveData: ConnectionLiveData
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var preference: MyPreference

    //ViewModel
    lateinit var vmFactory: LoginViewModelFactory
    lateinit var loginViewModel: LoginViewModel
    var keyboardFlag = false

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
            )
        )
        loginViewModel = ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)



        binding.cancelIc.setOnClickListener {
            findNavController(this)?.popBackStack()

        }

        binding.loginBtn.setOnClickListener {
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().getWindowToken(), 0)

            if (InternetConnection.isInternetAvailable(requireContext())){
                if (emailEditText.text.toString()!=""&&passwordEditText.text.toString()!=""){
                    binding.progressbar.visibility = View.VISIBLE
                    loginViewModel.getAllUsers()
                    loginViewModel.customersResponseLiveData.observe(viewLifecycleOwner){
                        Log.i("login","${it.body()?.customers?.size}")

                        if(it.isSuccessful){
                            var check =false
                            for (user in it.body()?.customers!!){
                                if (emailEditText.text.toString().trim().equals(user.email,ignoreCase = true)&&user.tags==passwordEditText.text.toString()){
                                    check = true
                                    //save user is data
                                    preference.saveData(Constants.USERID, user.id.toString())
                                    preference.saveData(Constants.USEREMAIL, user.email.toString())
                                    preference.saveData(Constants.USERFIRSTNAME, user.first_name.toString())
                                    preference.saveData(Constants.USERMOBILEPHONE, user.phone.toString())
                                    preference.saveData(Constants.TOCURRENCY,"EGP")
                                    preference.saveData(Constants.CURRENCYRESULT,"1.0")
                                    binding.progressbar.visibility = View.GONE
                                    startActivity(Intent(requireContext(), MainTabsActivity::class.java))
                                    activity?.finish()

                                }

                            }
                            if (!check){
                                Toast.makeText(requireContext(), "No user exist with that data", Toast.LENGTH_SHORT).show()
                                binding.progressbar.visibility = View.GONE

                            }

                        }
                        else{
                            Toast.makeText(requireContext(), "Error Connection", Toast.LENGTH_SHORT).show()
                            binding.progressbar.visibility = View.GONE

                        }

                    }
                }else{
                    Toast.makeText(requireContext(), "Please Complete Your Fields At first", Toast.LENGTH_SHORT).show()

                }

            }else{
                showSnackBar("Please check your internet connection")
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

    private fun showSnackBar(msg:String){
        val snackBar = Snackbar.make(requireActivity().findViewById(R.id.content),
            msg, Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }





}