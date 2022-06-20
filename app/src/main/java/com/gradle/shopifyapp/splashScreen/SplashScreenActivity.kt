package com.gradle.shopifyapp.splashScreen

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gradle.shopifyapp.authentication.MainActivity
import com.gradle.shopifyapp.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val splashScreenScope = lifecycleScope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        splashScreenScope.launch(Dispatchers.Default) {
            delay(2000)
            val intent = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onPause() {
        super.onPause()
        splashScreenScope.cancel()
    }
}