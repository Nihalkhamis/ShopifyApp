package com.gradle.shopifyapp.settings

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivitySettingsBinding
import com.gradle.shopifyapp.utils.Constants


class SettingsActivity : AppCompatActivity() {

    private var binding: ActivitySettingsBinding? = null
    var address = ""
    var city = ""
    var country = ""
    var zipCode = ""
    var resultFromMaps = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

       if (intent.getStringExtra(Constants.ADDRESS)?.isNotEmpty() == true){
           //go to addAddress fragment
               address = intent.getStringExtra(Constants.ADDRESS)!!
               city = intent.getStringExtra(Constants.CITY)!!
               country = intent.getStringExtra(Constants.COUNTRY)!!
               zipCode = intent.getStringExtra(Constants.ZIPCODE)!!
               resultFromMaps = intent.getStringExtra(Constants.FROMMAPSTO)!!

       }



    }


}