package com.gradle.shopifyapp.authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreference: MyPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPreference = MyPreference.getInstance(this)!!
        val check = sharedPreference.getData(Constants.USERID)

        if(!check.isNullOrEmpty()){
            startActivity(Intent(this,MainTabsActivity::class.java))
        }

    }
}