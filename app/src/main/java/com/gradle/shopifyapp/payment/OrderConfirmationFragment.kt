package com.gradle.shopifyapp.payment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentOrderConfirmationBinding
import com.gradle.shopifyapp.databinding.FragmentStartingBinding
import com.kotlin.weatherforecast.utils.Constants
import com.kotlin.weatherforecast.utils.MyPreference


class OrderConfirmationFragment : Fragment() {
    private var _binding: FragmentOrderConfirmationBinding? = null
    private val binding get() = _binding!!
    lateinit var preference: MyPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOrderConfirmationBinding.inflate(inflater, container, false)
        preference = MyPreference.getInstance(requireContext())!!

//        _binding!!.toAddressArrow.setOnClickListener {
//            findNavController(this)?.navigate(R.id.address_fragment)
//        }

        _binding!!.nameTextView.text = preference.getData(Constants.USERFIRSTNAME)
        _binding!!.emailTextView.text = preference.getData(Constants.USEREMAIL)
       // _binding!!.phoneTextView.text = preference.getData(Constants.USERFIRSTNAME)


        val root: View = binding.root
        return root
    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

}