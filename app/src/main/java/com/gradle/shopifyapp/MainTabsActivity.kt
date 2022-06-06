package com.gradle.shopifyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.gradle.shopifyapp.databinding.ActivityMainTabsBinding
import com.gradle.shopifyapp.search.views.SearchActivity
import com.gradle.shopifyapp.shoppingCart.View.ShoppingCartActivity
import com.gradle.shopifyapp.wishlist.view.WishlistActivity

class MainTabsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainTabsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainTabsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main_tabs)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_category, R.id.navigation_me
            )
        )


        binding.topBar.setNavigationOnClickListener {
             val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
          //  Toast.makeText(this,"Search Tap",Toast.LENGTH_LONG).show()
        }
        binding.topBar.setOnMenuItemClickListener { menuItem ->

            when(menuItem.itemId){
                R.id.favorite ->{
                    val intent = Intent(this, WishlistActivity::class.java).apply {
                    }
                    startActivity(intent)
                   // Toast.makeText(this,"Favorite Tap",Toast.LENGTH_LONG).show()
                     true

                }

                R.id.cart -> {
                    val intent = Intent(this, ShoppingCartActivity::class.java).apply {
                    }
                    startActivity(intent)
                   // Toast.makeText(this, "Cart Tap", Toast.LENGTH_LONG).show()
                     true
                }
                else ->
                {
                     false
                }
            }
        }


        //setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}