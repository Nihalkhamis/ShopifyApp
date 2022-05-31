package com.gradle.shopifyapp.productBrand.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityProductBrandBinding
import com.gradle.shopifyapp.model.ProductModel

class ProductBrandActivity : AppCompatActivity(), OnItemClickListener {

    private var binding: ActivityProductBrandBinding? = null


    lateinit var productBrandAdapter: ProductBrandAdapter
    lateinit var productBrandsList : ArrayList<ProductModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_brand)

        binding = ActivityProductBrandBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

     setAdapter()

    }

    private fun setAdapter() {
   
    }

    override fun onClick(productModel: ProductModel) {

    }
}