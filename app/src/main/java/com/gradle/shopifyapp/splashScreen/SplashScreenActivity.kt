package com.gradle.shopifyapp.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.gradle.shopifyapp.MainTabsActivity
import com.gradle.shopifyapp.authentication.MainActivity
import com.gradle.shopifyapp.databinding.ActivitySplashScreenBinding
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreenScope = lifecycleScope
    lateinit var sharedPreference: MyPreference
//    lateinit var connectionLiveData: ConnectionLiveData


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        connectionLiveData = ConnectionLiveData(this)

//        connectionLiveData.observe(this){
//            isNetworkAvailable->{
//                if(isNetworkAvailable){
//
//                }else{
//
//                }
//        }
//        }

        splashScreenScope.launch(Dispatchers.Default) {
            delay(2000)

            sharedPreference = MyPreference.getInstance(this@SplashScreenActivity)!!
            val check = sharedPreference.getData(Constants.USERID)

            if(check.isNullOrEmpty()){
                val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this@SplashScreenActivity, MainTabsActivity::class.java)
                startActivity(intent)
                finish()
            }

        }

    }

    override fun onPause() {
        super.onPause()
        splashScreenScope.cancel()
    }
}