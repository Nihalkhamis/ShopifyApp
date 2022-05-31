package com.gradle.shopifyapp.authentication.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentLoginBinding
import com.gradle.shopifyapp.databinding.FragmentSignUpBinding


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText
    lateinit var loginBtn: Button
    lateinit var signUpBtn: Button
    lateinit var backBtn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        backBtn = binding.cancelIc
        backBtn.setOnClickListener {
            findNavController(this)?.popBackStack()

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