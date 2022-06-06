package com.gradle.shopifyapp.wishlist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.category.view.SubCategoryAdapter
import com.gradle.shopifyapp.databinding.ActivityProductBrandBinding
import com.gradle.shopifyapp.databinding.ActivityWishlistBinding
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.model.SubCategoryModel
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productBrand.view.OnItemClickListener
import com.gradle.shopifyapp.productBrand.view.ProductBrandAdapter
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModel
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModelFactory
import com.gradle.shopifyapp.wishlist.viewmodel.WishlistViewModel
import com.gradle.shopifyapp.wishlist.viewmodel.WishlistViewModelFactory

class WishlistActivity : AppCompatActivity(), OnItemClickListener {

    private var binding: ActivityWishlistBinding? = null

    //lateinit var wishlistAdapter: ProductBrandAdapter

    // static data
    private lateinit var wishlistAdapter: SubCategoryAdapter

    private lateinit var vmFactory: WishlistViewModelFactory
    //lateinit var wishlistViewModel: WishlistViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        binding!!.backBtn.setOnClickListener {
            finish()
        }

        vmFactory = WishlistViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )

       // wishlistViewModel = ViewModelProvider(this, vmFactory).get(WishlistViewModel::class.java)

        //call method in viewModel to fetch data

        setAdapter()

        //observe liveData list



    }

    private fun setAdapter() {
       // wishlistAdapter = ProductBrandAdapter(this, ArrayList(), this)

        // static data
        wishlistAdapter = SubCategoryAdapter(this, ArrayList())
        wishlistAdapter.setSubCategories(arrayListOf(SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
            ))
        binding!!.wishlistProductRV.adapter = wishlistAdapter
    }

    override fun onClick(productModel: Product) {

    }
}