package com.gradle.shopifyapp.productBrand.view

import android.os.Bundle
import android.provider.SyncStateContract
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityProductBrandBinding
import com.gradle.shopifyapp.home.viewmodel.HomeViewModel
import com.gradle.shopifyapp.home.viewmodel.HomeViewModelFactory
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModel
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModelFactory
import com.kotlin.weatherforecast.utils.Constants

class ProductBrandActivity : AppCompatActivity(), OnItemClickListener {

    private var binding: ActivityProductBrandBinding? = null


    lateinit var productBrandAdapter: ProductBrandAdapter

    lateinit var vmFactory: ProductBrandViewModelFactory
    lateinit var homeViewModel: ProductBrandViewModel

    lateinit var brandID: String
    var searchIconClicked : Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_product_brand)

        binding = ActivityProductBrandBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        brandID = intent.getStringExtra(Constants.BRANDID).toString()
        Log.d("TAG", "ID od Brand Inside activity: $brandID")
        binding!!.subcategoryNameTxt.text = intent.getStringExtra(Constants.BRANDNAME).toString()

        binding!!.searchBtn.setOnClickListener {
            if (searchIconClicked){
                searchIconClicked = false
                binding!!.searchEt.visibility = View.VISIBLE
                binding!!.subcategoryNameTxt.visibility = View.GONE
            }
          else{
                searchIconClicked = true
                binding!!.searchEt.visibility = View.GONE
                binding!!.subcategoryNameTxt.visibility = View.VISIBLE

            }
        }

        binding!!.backBtn.setOnClickListener {
            finish()
        }

        vmFactory = ProductBrandViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )

        homeViewModel = ViewModelProvider(this, vmFactory).get(ProductBrandViewModel::class.java)
        homeViewModel.getAllBrandsProducts(this, brandID!!)

        setAdapter()

        homeViewModel.liveDataBrandsProductList.observe(this) {
            Log.d("TAG", "onCreateView: $it")
            productBrandAdapter.setProductsBrand(it.products)
        }

    }


    private fun setAdapter() {
        productBrandAdapter = ProductBrandAdapter(this, ArrayList(), this)
        binding!!.productBrandRV.adapter = productBrandAdapter
    }

    override fun onClick(productModel: Product) {

    }
}