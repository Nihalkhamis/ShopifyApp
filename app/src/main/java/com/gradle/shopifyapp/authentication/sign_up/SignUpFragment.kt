package com.gradle.shopifyapp.authentication.sign_up

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.databinding.FragmentSignUpBinding


class SignUpFragment : Fragment() {

    private var _binding : FragmentSignUpBinding? = null
    private val binding get() = _binding!!
    lateinit var backBtn:ImageView
    lateinit var emailTxt:EditText
    lateinit var passwordTxt:EditText
    lateinit var confirmPasswordTxt:EditText
    lateinit var firstName:EditText
    lateinit var lastName:EditText
    lateinit var signUpBtn:Button

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

        signUpBtn = binding.signupBtn
        val root: View = binding.root
        return root
    }



    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }
}