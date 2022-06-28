package com.gradle.shopifyapp.productBrand.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.databinding.ActivityProductBrandBinding
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModel
import com.gradle.shopifyapp.productBrand.viewmodel.ProductBrandViewModelFactory
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.shoppingCart.View.ShoppingCartActivity
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.wishlist.view.WishlistActivity

class ProductBrandActivity : AppCompatActivity(), OnItemClickListener {

    private var binding: ActivityProductBrandBinding? = null

    //for internet connection
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : AlertDialog



    lateinit var productBrandAdapter: ProductBrandAdapter

    lateinit var vmFactory: ProductBrandViewModelFactory
    lateinit var homeViewModel: ProductBrandViewModel
    var myProducts: List<Product>? = null
    private lateinit var brandID: String
    private lateinit var brandName: String
    var searchIconClicked: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_product_brand)

        binding = ActivityProductBrandBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        //for internet connection
        connectionLiveData = ConnectionLiveData(this)
        dialog = Alert.makeAlert(this)



        brandID = intent.getStringExtra(Constants.BRANDID).toString()
        brandName = intent.getStringExtra(Constants.BRANDNAME).toString()

        Log.d("TAG", "ID od Brand Inside activity: $brandID")
        binding!!.subcategoryNameTxt.text = brandName
        binding!!.cartImg.setOnClickListener {
            startActivity(Intent(this,ShoppingCartActivity::class.java))

        }

        binding!!.searchBtn.setOnClickListener {
            if (searchIconClicked) {
                searchIconClicked = false
                binding!!.searchEt.visibility = View.VISIBLE
                binding!!.subcategoryNameTxt.visibility = View.GONE

                binding!!.searchEt.doOnTextChanged { text, start, count, after ->
                    Log.i("searchData", text.toString())
                    var f =
                        myProducts?.filter { it.title!!.contains(text.toString(), ignoreCase = true) }
                    f?.let { productBrandAdapter.setProductsBrand(it) }
                    productBrandAdapter.notifyDataSetChanged()
                }


            } else {
                searchIconClicked = true
                binding!!.searchEt.visibility = View.GONE
                binding!!.subcategoryNameTxt.visibility = View.VISIBLE
                myProducts?.let { productBrandAdapter.setProductsBrand(it) }
                productBrandAdapter.notifyDataSetChanged()
                binding!!.searchEt.setText("")


            }
        }

        binding!!.backBtn.setOnClickListener {
            finish()
        }

        binding!!.favImg.setOnClickListener {
            val intent = Intent(this, WishlistActivity::class.java).apply {
            }
            startActivity(intent)
        }

        vmFactory = ProductBrandViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )

        homeViewModel = ViewModelProvider(this, vmFactory).get(ProductBrandViewModel::class.java)

        connectionLiveData.observe(this){
            isNetworkAvaliable->
            if (isNetworkAvaliable){
                dialog.dismiss()
                homeViewModel.getAllBrandsProducts(this,"", "", brandName)

                setAdapter()

                homeViewModel.liveDataBrandsProductList.observe(this) {
                    Log.d("TAG", "onCreateView: $it")
                    myProducts = it.products
                    productBrandAdapter.setProductsBrand(it.products)
                }

                homeViewModel.loading.observe(this, Observer {
                    if (it) {
                        binding!!.progressbar.visibility = View.VISIBLE
                    } else {
                        binding!!.progressbar.visibility = View.GONE
                    }
                })
            }else{
                dialog.show()
            }
        }

    }


    private fun setAdapter() {
        productBrandAdapter = ProductBrandAdapter(this, ArrayList(), this)
        binding!!.productBrandRV.adapter = productBrandAdapter
    }

    override fun onClick(productModel: Product,price:String) {
        Log.i("djnsk",productModel.toString())
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("product",productModel)
        intent.putExtra("price",price)
        startActivity(intent)
    }


}