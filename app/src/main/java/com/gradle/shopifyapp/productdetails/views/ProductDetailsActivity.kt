package com.gradle.shopifyapp.productdetails.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewParent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityProductDetailesBinding
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.NoteAttribute
import com.gradle.shopifyapp.home.viewmodel.HomeViewModel
import com.gradle.shopifyapp.home.viewmodel.HomeViewModelFactory
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.model.ReviewModel
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productdetails.viewmodel.ProductDetailsViewModel
import com.gradle.shopifyapp.productdetails.viewmodel.ProductDetailsViewModelFactory
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

    //viewModel
    lateinit var vmFactory: ProductDetailsViewModelFactory
    lateinit var productDetailsVm: ProductDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        vmFactory = ProductDetailsViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )


        productDetailsVm = ViewModelProvider(this, vmFactory).get(ProductDetailsViewModel::class.java)


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

        binding.addToCartBtn.setOnClickListener{
            productDetailsVm.liveDraftOrderList.observe(this) {
                Log.d("TAG", "onCreateView: ${it}")
                var order = DraftOrder()
                order.email = "shimaa22@gmail.com"
                order.note = "cart"
                var note_attribute = NoteAttribute()
                note_attribute.name = "image"
                note_attribute.value = product.images[0].src
                order.note_attributes = listOf(note_attribute)
                order.line_items?.get(0)?.quantity = 1
                for(i in 0..product.variants.size-1){
                 /*   if(product.variants[0].option1 == selectedSize &&
                        product.variants[0].option2 ==   ){

                    }*/
                }


            }
        }

    }

    override fun onClick(size: String) {
        selectedSize = size
        Log.i("size", size)
        sizeAdapter.notifyDataSetChanged()
    }


}