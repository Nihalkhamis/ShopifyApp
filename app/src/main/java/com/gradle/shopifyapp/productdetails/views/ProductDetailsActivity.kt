package com.gradle.shopifyapp.productdetails.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewParent
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityProductDetailesBinding


class ProductDetailsActivity : AppCompatActivity() {
    lateinit var viewPager:ViewPager
    lateinit var  dots: DotsIndicator
    lateinit var productSliderAdapter: ProductViewPagerAdapter
    lateinit var binding: ActivityProductDetailesBinding
    lateinit var images: List<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        images = listOf("https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067"
            ,"https://cdn.shopify.com/s/files/1/0589/7509/2875/products/8a029d2035bfb80e473361dfc08449be.jpg?v=1653403067"
            ,"https://cdn.shopify.com/s/files/1/0589/7509/2875/products/ad50775123e20f3d1af2bd07046b777d.jpg?v=1653403067"
            ,"https://cdn.shopify.com/s/files/1/0589/7509/2875/products/85cc58608bf138a50036bcfe86a3a362.jpg?v=1653403067")
        viewPager =binding.productViewPager
        dots = binding.dots
        productSliderAdapter = ProductViewPagerAdapter(this,images)
        viewPager.adapter = productSliderAdapter
        dots.attachViewPager(viewPager)
        binding.descriptionText.text = "The Stan Smith owned the tennis court in the '70s. Today it runs the streets with the same clean, classic style. These kids' shoes preserve the iconic look of the original, made in leather with punched 3-Stripes, heel and tongue logos and lightweight step-in cushioning."


    }
}