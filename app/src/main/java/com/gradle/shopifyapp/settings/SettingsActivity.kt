package com.gradle.shopifyapp.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gradle.shopifyapp.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {

    private var binding: ActivitySettingsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)


    }


}