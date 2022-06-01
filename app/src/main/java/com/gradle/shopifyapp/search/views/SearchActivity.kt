package com.gradle.shopifyapp.search.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.databinding.ActivitySearchBinding
import com.gradle.shopifyapp.home.view.HomeFragment
import com.gradle.shopifyapp.me.view.OrderListViewAdapter
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel

class SearchActivity : AppCompatActivity(),OnItemClickListener {
    lateinit var binding :ActivitySearchBinding
     lateinit var searchTxt :EditText
     lateinit var searchRecycleView:RecyclerView
    lateinit var searchRecyclerAdapter: ProductBrandAdapter
    var products =HomeFragment.myProducts


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        searchRecycleView = binding.searchList
        searchTxt =binding.searchEditText
        searchRecyclerAdapter = ProductBrandAdapter(this, ArrayList(),this)
       // products?.let { searchRecyclerAdapter.setProductsBrand(it) }
        searchRecycleView.adapter = searchRecyclerAdapter
        searchTxt.doOnTextChanged{ text, start, count, after ->
        Log.i("searchData",text.toString())
          searchRecyclerAdapter.setProductsBrand(ArrayList())
           var f =  products?.filter { it.title.contains(text.toString())}
            f?.let { searchRecyclerAdapter.setProductsBrand(it) }
            searchRecyclerAdapter.notifyDataSetChanged()
        }


    }



    override fun onClick(productModel: Product) {
        TODO("Not yet implemented")
        Log.i("taged","Clicked")

    }
}