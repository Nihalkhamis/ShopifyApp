package com.gradle.shopifyapp.authentication.starting

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentStartingBinding


class StartingFragment : Fragment() {

    lateinit var signUpButton: Button
    lateinit var loginText: TextView
    lateinit var skipText: TextView
    private var _binding: FragmentStartingBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartingBinding.inflate(inflater, container, false)
        signUpButton = binding.signupBtn
        signUpButton.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToSignup)
        }

        loginText = binding.loginTxt
        loginText.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToLogin)

        }
        skipText = binding.skipTxt

        binding.skipTxt.setOnClickListener {
            startActivity(Intent(requireContext(),MainTabsActivity::class.java))
            activity?.finish()
        }

        val root: View = binding.root
        return root
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }


}