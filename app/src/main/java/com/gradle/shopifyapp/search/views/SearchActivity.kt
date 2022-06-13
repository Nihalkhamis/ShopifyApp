package com.gradle.shopifyapp.search.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.databinding.ActivitySearchBinding
import com.gradle.shopifyapp.home.view.HomeFragment
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.productBrand.view.OnItemClickListener
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.productBrand.view.ProductBrandAdapter

class SearchActivity : AppCompatActivity(), OnItemClickListener {
    lateinit var binding: ActivitySearchBinding
    lateinit var searchTxt: EditText
    lateinit var searchRecycleView: RecyclerView
    lateinit var searchRecyclerAdapter: ProductBrandAdapter
    lateinit var cancelBtn:Button
    var products = HomeFragment.myProducts


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchRecycleView = binding.searchList
        searchTxt = binding.searchEditText
        cancelBtn=binding.cancel

        cancelBtn.setOnClickListener {
            finish()
        }

        searchRecyclerAdapter = ProductBrandAdapter(this, ArrayList(), this)
        searchRecycleView.adapter = searchRecyclerAdapter
        searchTxt.doOnTextChanged { text, start, count, after ->
            searchRecyclerAdapter.setProductsBrand(ArrayList())
            var f = products?.filter { it.title.contains(text.toString(), ignoreCase = true) }
            f?.let { searchRecyclerAdapter.setProductsBrand(it) }
            searchRecyclerAdapter.notifyDataSetChanged()
        }



    }


    override fun onClick(productModel: Product,price:String) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product",productModel)
        intent.putExtra("price",price)
        startActivity(intent)
    }
}