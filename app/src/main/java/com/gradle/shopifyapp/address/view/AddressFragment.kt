package com.gradle.shopifyapp.address.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentAddressBinding
import com.gradle.shopifyapp.payment.view.OrderConfirmationFragment

class AddressFragment : Fragment() {
    private var _binding: FragmentAddressBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddressBinding.inflate(inflater, container, false)

//        _binding!!.addressRow.setOnClickListener {
//            findNavController(this)?.navigate(R.id.addresstopayment)
//        }

        _binding!!.paymentBtn.setOnClickListener {
            val line_items = requireArguments().getSerializable("line_items")
            val total_prices = requireArguments().getSerializable("total_prices")

            val mFragmentManager = requireActivity().supportFragmentManager
            val mFragmentTransaction = mFragmentManager.beginTransaction()
            val mFragment = OrderConfirmationFragment()

            var bundle = Bundle()
            bundle.putSerializable("line_items",line_items)
            bundle.putSerializable("total_prices",total_prices)
            mFragment.arguments = bundle
            mFragmentTransaction.replace(R.id.payment_nav, mFragment).commit()

        }

        val root: View = binding.root
        return root    }

    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }


}