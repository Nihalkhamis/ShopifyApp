package com.gradle.shopifyapp.wishlist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.ActivityWishlistBinding
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.home.view.HomeFragment
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.shoppingCart.View.CartOnClickListener
import com.gradle.shopifyapp.shoppingCart.View.ShoppingCartActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class WishlistActivity : AppCompatActivity(), CartOnClickListener {

    private var binding: ActivityWishlistBinding? = null

    private lateinit var wishlistAdapter: WishlistAdapter

    private lateinit var vmFactory: ShoppingCartViewModelFactory
    lateinit var viewModel: ShoppingCartViewModel

    lateinit var preference: MyPreference

    var favProducts: ArrayList<Draft_order> = ArrayList<Draft_order>()

    var lineItems = ArrayList<LineItem>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wishlist)

        binding = ActivityWishlistBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        preference = MyPreference.getInstance(this)!!


        binding!!.backBtn.setOnClickListener {
            finish()
        }
        binding!!.cartImg.setOnClickListener {
            startActivity(Intent(this,ShoppingCartActivity::class.java))
        }

        vmFactory = ShoppingCartViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )

        viewModel = ViewModelProvider(this, vmFactory).get(ShoppingCartViewModel::class.java)


        setAdapter()
        getFavProducts()

    }

    private fun setAdapter() {

        wishlistAdapter = WishlistAdapter(this, this)
        binding!!.wishlistProductRV.adapter = wishlistAdapter
//        wishlistAdapter.setSubCategories(arrayListOf(SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            SubCategoryModel("Soiree red dress",R.drawable.dress,2000),
//            ))
    }

    private fun getFavProducts(){
        viewModel.getDraftOrder(this)
        viewModel.liveDraftOrderList.observe(this) {
            Log.d("TAG", "getFavProducts: ${it.size}")
            for(i in 0..it.size-1){
                var email = preference.getData(Constants.USEREMAIL)

                var df = Draft_order()
                if(it.get(i).email == email && it.get(i).note == "favourite")
                {
                    df.draft_order = it.get(i)
                    favProducts.add(df)
                    lineItems.add(df.draft_order!!.line_items!![0])
                }
            }

//            for(lineItem in favProducts!!){
//                var product = HomeFragment.myProducts?.filter {
//                    it.id == lineItem.draft_order!!.line_items!![0].product_id
//                }
//                Log.d("TAG", "getFavProducts: $product")
//                //favProductsList.add(product)
//            }
          wishlistAdapter.setFavProducts(favProducts)
        }


        viewModel.loading.observe(this, Observer {
            if (it) {
                binding!!.progressbar.visibility = View.VISIBLE
            } else {
                binding!!.progressbar.visibility = View.GONE
            }
        })
    }

    override fun onAddProduct(draftOrder: Draft_order) {

    }

    override fun onRemoveProduct(draftOrder: Draft_order) {
    }

    override fun onDeleteProduct(id: String) {
    }

    override fun onClickProduct(draftOrder: Draft_order) {
        Log.d("TAG", "onClickProduct: ${draftOrder.draft_order?.line_items?.get(0)?.product_id}")
        val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra(Constants.SELECTEDPRODUCTID, draftOrder.draft_order?.line_items?.get(0)?.product_id)
            intent.putExtra(Constants.FROMWISHLIST,"true")

        Log.d("TAG", "onClickProduct: ${intent.getLongExtra(Constants.SELECTEDPRODUCTID,1000)}")
        Log.d("TAG", "onClickProduct: ${intent.getStringExtra(Constants.FROMWISHLIST)}")

        startActivity(intent)
    }


}