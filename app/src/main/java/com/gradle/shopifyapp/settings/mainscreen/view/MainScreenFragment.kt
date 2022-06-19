package com.gradle.shopifyapp.settings.mainscreen.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.authentication.MainActivity
import com.gradle.shopifyapp.databinding.FragmentMainScreenBinding
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class MainScreenFragment : Fragment() {

    private var _binding: FragmentMainScreenBinding? = null

    private val binding get() = _binding!!
    lateinit var preference : MyPreference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if ((requireActivity() as SettingsActivity).address.isNotEmpty()){
            findNavController(this)?.navigate(R.id.fragmentToAddAddress)
        }

        preference = MyPreference.getInstance(requireContext())!!
        binding.backBtn.setOnClickListener {
            activity?.finish()
        }

        binding.currencyTxt.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToCurrency)
        }

        binding.addressTxt.setOnClickListener {
            findNavController(this)?.navigate(R.id.fragmentToAddress)
        }
        binding.logoutTxt.setOnClickListener {
            preference.saveData(Constants.USERID,"")
            preference.saveData(Constants.USEREMAIL,"")
            preference.saveData(Constants.USERID,"")
            preference.saveData(Constants.USERFIRSTNAME,"")
            preference.saveData(Constants.TOCURRENCY,"EGP")
            preference.saveData(Constants.USERMOBILEPHONE,"")
           // preference.saveData(Constants.CURRENCYRESULT,"")
            startActivity(Intent(requireContext(),MainActivity::class.java))
            activity?.finish()
        }
    }

    private fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }
}