package com.gradle.shopifyapp.productdetails.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewParent
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityProductDetailesBinding
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ReviewModel
import com.gradle.shopifyapp.shoppingCart.View.ShoppingCartActivity


class ProductDetailsActivity : AppCompatActivity(), OnclickInterface {
    lateinit var viewPager: ViewPager
    lateinit var dots: DotsIndicator
    lateinit var productSliderAdapter: ProductViewPagerAdapter

    lateinit var sizeAdapter: SizeRecyclerAdapter
    lateinit var sizeRecyclerView: RecyclerView


    lateinit var reviewAdapter: ReviewRecyclerAdapter
    lateinit var reviewRecyclerView: RecyclerView
    lateinit var reviews: List<ReviewModel>


    lateinit var product: Product
    lateinit var binding: ActivityProductDetailesBinding
    lateinit var selectedSize: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        product = intent.getSerializableExtra("product") as Product


        viewPager = binding.productViewPager
        dots = binding.dots
        productSliderAdapter = ProductViewPagerAdapter(this, product.images)
        viewPager.adapter = productSliderAdapter
        dots.attachViewPager(viewPager)

        sizeAdapter = SizeRecyclerAdapter(this, product.options[0].values, this)
        selectedSize = product.options[0].values[0]
        sizeRecyclerView = binding.sizeRecyclerView
        sizeRecyclerView.adapter = sizeAdapter


        reviews = listOf(
            ReviewModel(userName = "Ahmed", userReview = "Very good Product"),
            ReviewModel(userName = "Moamen", userReview = "I like it"),
            ReviewModel(
                userName = "Rania",
                userReview = "i do not like the material but it is a good price"
            ),
            ReviewModel(userName = "Azza", userReview = "good price")
        )
        reviewAdapter = ReviewRecyclerAdapter(reviews)
        reviewRecyclerView = binding.reviewRecyclerView
        reviewRecyclerView.adapter = reviewAdapter

        binding.descriptionText.text = product.body_html
        binding.productType.text = product.product_type
        binding.productName.text = product.title
        binding.productPrice.text = product.variants[0].price
        binding.backBtn.setOnClickListener {
            finish()
        }
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }

    override fun onClick(size: String) {
        selectedSize = size
        Log.i("size", size)
        sizeAdapter.notifyDataSetChanged()
    }


}