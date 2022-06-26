package com.gradle.shopifyapp.authentication.sign_up.view

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
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.authentication.sign_up.viewmodel.SignUpViewModel
import com.gradle.shopifyapp.authentication.sign_up.viewmodel.SignUpViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentSignUpBinding
import com.gradle.shopifyapp.model.Customer
import com.gradle.shopifyapp.model.CustomerModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


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
    lateinit var connectionLiveData: ConnectionLiveData



    lateinit var preference: MyPreference

    //viewModel
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



        //viewModel
        vmFactory = SignUpViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            )
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

            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(requireView().windowToken, 0)

            if(firstName.text.toString()!=""&&lastName.text.toString()!=""&&emailTxt.text.toString()!=""&&phoneTxt.text.toString()!=""&&passwordTxt.text.toString()!=""&& confirmPasswordTxt.text.toString()!=""){
                if(passwordTxt.text.toString() == confirmPasswordTxt.text.toString()){
                    if(InternetConnection.isInternetAvailable(requireContext())){
                        c.first_name = firstName.text.toString().trim()
                        c.last_name = lastName.text.toString().trim()
                        c.email = emailTxt.text.toString().trim()
                        c.phone = phoneTxt.text.toString().trim()
                        c.password = passwordTxt.text.toString().trim()
                        c.tags="${passwordTxt.text.toString().trim()}"
                        c.password_confirmation = confirmPasswordTxt.text.toString().trim()
                        c.verified_email = true
                        myCustomer.customer = c
                        signUpViewModel.registerUser(myCustomer)
                        binding.progressbar.visibility = View.VISIBLE

                        signUpViewModel.userResponseLiveData.observe(viewLifecycleOwner) {
                            if (it.isSuccessful) {
                                Toast.makeText(requireContext(),"Successfully Register", Toast.LENGTH_LONG).show()
                                //save user is data
                                preference.saveData(Constants.USERID, it.body()?.customer?.id.toString())
                                preference.saveData(Constants.USEREMAIL, it.body()?.customer?.email.toString())
                                preference.saveData(Constants.USERFIRSTNAME, it.body()?.customer?.first_name.toString())
                                preference.saveData(Constants.USERMOBILEPHONE, it.body()?.customer?.phone.toString())
                                preference.saveData(Constants.TOCURRENCY,"EGP")
                                preference.saveData(Constants.CURRENCYRESULT,"1.0")

                                binding.progressbar.visibility = View.GONE
                                startActivity(Intent(requireContext(), MainTabsActivity::class.java))
                                activity?.finish()

                            } else  {
                                binding.progressbar.visibility = View.GONE
                                Log.i("error", it.code().toString())
                                Log.i("error", it.errorBody().toString())
                                Toast.makeText(requireContext(), "Error while registering check this data is not exist before", Toast.LENGTH_LONG).show()
                            }
                        }
                    }else{
                        showSnackBar("Please check your internet connection ")
                    }

                }else{
                    confirmPasswordTxt.requestFocus()
                    Toast.makeText(requireContext(), "Please Confirm Your Password", Toast.LENGTH_LONG).show()
                }
            }else{
                Toast.makeText(requireContext(), " Please complete your required fields at first ", Toast.LENGTH_LONG).show()
            }

        }
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


    override fun onStart() {
        super.onStart()
        var  firstTime = true
        connectionLiveData = ConnectionLiveData(requireContext())
        connectionLiveData.observe(viewLifecycleOwner){
            if (it){
                // for not making it at the first time when entre the activity
                if(!firstTime){
                    showSnackBar("We are back online")

                }else{
                    firstTime = false
                }

            }else{
                showSnackBar("Be careful we lost the connection")

            }
        }
    }

}