package com.gradle.shopifyapp.address.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gradle.shopifyapp.databinding.FragmentAddAddressBinding


class AddAddressFragment : Fragment() {

    private var _binding: FragmentAddAddressBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        val root: View = binding.root


        // Inflate the layout for this fragment
        return root
    }


}