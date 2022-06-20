package com.gradle.shopifyapp.settings.mainscreen.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.authentication.MainActivity
import com.gradle.shopifyapp.databinding.FragmentMainScreenBinding
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
        preference = MyPreference.getInstance(requireContext())!!

        if (!checkUserExist()){
            binding.logoutTxt.text ="Login"
            binding.logoutTxt.setCompoundDrawablesWithIntrinsicBounds(R.drawable.login_icon, 0, 0, 0);
        }

        binding.backBtn.setOnClickListener {
            activity?.finish()
        }

        binding.currencyTxt.setOnClickListener {
            if (checkUserExist()){
                findNavController(this)?.navigate(R.id.fragmentToCurrency)
            }else{
                makeAlert()
            }
        }

        binding.addressTxt.setOnClickListener {

            if (checkUserExist()){
                findNavController(this)?.navigate(R.id.fragmentToAddress)
            }else{
                makeAlert()
            }
        }
        binding.logoutTxt.setOnClickListener {
            if(checkUserExist()){
                preference.saveData(Constants.USERID,"")
                preference.saveData(Constants.USEREMAIL,"")
                preference.saveData(Constants.USERID,"")
                preference.saveData(Constants.USERFIRSTNAME,"")
                preference.saveData(Constants.TOCURRENCY,"EGP")
                preference.saveData(Constants.CURRENCYRESULT,"1.0")
                preference.saveData(Constants.USERMOBILEPHONE,"")
                Log.i("login","already login")
            }
            val intent = Intent(requireContext(),MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }

    private fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    private fun makeAlert(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Warning")
        builder.setMessage("Sorry but you have to login at first")
        builder.setNeutralButton("Cancel") { dialog, which -> }
        builder.setPositiveButton("Login"){dialogInterface, which ->
            var intent =Intent(requireContext(),MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

        builder.show()
    }

    private fun checkUserExist():Boolean{
        var check = true
        if (preference.getData(Constants.USEREMAIL).isNullOrEmpty()){
            check =false
        }
        return check
    }
}