package com.gradle.shopifyapp.productdetails.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewParent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.gradle.shopifyapp.databinding.ActivityProductDetailesBinding
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.LineItem
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
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class ProductDetailsActivity : AppCompatActivity(), OnclickInterface {
    lateinit var viewPager: ViewPager
    lateinit var dots: DotsIndicator
    lateinit var productSliderAdapter: ProductViewPagerAdapter

    lateinit var sizeAdapter: SizeRecyclerAdapter
    lateinit var sizeRecyclerView: RecyclerView


    lateinit var reviewAdapter: ReviewRecyclerAdapter
    lateinit var reviewRecyclerView: RecyclerView
    lateinit var reviews: List<ReviewModel>

    lateinit var preference: MyPreference


    lateinit var colorAdapter: ColorRecyclerAdapter
    lateinit var colorRecyclerView: RecyclerView

    lateinit var product: Product
    lateinit var binding: ActivityProductDetailesBinding
    lateinit var selectedSize: String
    lateinit var selectedColor: String

   //viewModel
    lateinit var vmFactory: ProductDetailsViewModelFactory
    lateinit var productDetailsVm: ProductDetailsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = MyPreference.getInstance(this)!!

        vmFactory = ProductDetailsViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )
        productDetailsVm = ViewModelProvider(this, vmFactory).get(ProductDetailsViewModel::class.java)
        product = intent.getSerializableExtra("product") as Product

        // product Images
        viewPager = binding.productViewPager
        dots = binding.dots
        productSliderAdapter = ProductViewPagerAdapter(this, product.images)
        viewPager.adapter = productSliderAdapter
        dots.attachViewPager(viewPager)

        //size
        sizeAdapter = SizeRecyclerAdapter(this, product.options[0].values, this)
        selectedSize = product.options[0].values[0]
        sizeRecyclerView = binding.sizeRecyclerView
        sizeRecyclerView.adapter = sizeAdapter


        //color
        colorAdapter = ColorRecyclerAdapter(this, product.options[1].values, this)
        selectedColor = product.options[1].values[0]
        colorRecyclerView = binding.colorRecyclerView
        colorRecyclerView.adapter = colorAdapter


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

        //back btn
        binding.backBtn.setOnClickListener {
            finish()
        }

        //cart btn
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java).apply {
            }
            startActivity(intent)
        }

        binding.addToCartBtn.setOnClickListener{
                var order = DraftOrder()
            var draft_orders = Draft_order()
            order.email = preference.getData(Constants.USEREMAIL)
                order.note = "cart"
                for(i in 0..product.variants.size-1){
                    if(product.variants[i].option1 == selectedSize &&
                        product.variants[i].option2 ==  selectedColor ){
                            Log.i("option",selectedSize)
                        Log.i("option",selectedColor)

                        var lineItems = LineItem()
                        lineItems.quantity = 1
                        lineItems.variant_id = product.variants[i].id
                        order.line_items = listOf(lineItems)
                        var note_attribute = NoteAttribute()
                        note_attribute.name = "image"
                        note_attribute.value = product.images[0].src
                        order.note_attributes = listOf(note_attribute)
                        draft_orders = Draft_order(order)
                        productDetailsVm.postDraftOrder(draft_orders)
                        productDetailsVm.liveDraftOrderList.observe(this) { dOrder->
                            if(dOrder.isSuccessful){
                                Toast.makeText(this,"Added to cart",Toast.LENGTH_LONG).show()
                            }
                            else{
                                Log.d("TAG", "failed: ${dOrder.code()}")
                            }
                        }
                        break
                    }
                }
//            draft_orders = Draft_order(order)
//            productDetailsVm.postDraftOrder(draft_orders)
//            productDetailsVm.liveDraftOrderList.observe(this) { dOrder->
//                if(dOrder.isSuccessful){
//                    Log.d("TAG", "successful")
//                    Toast.makeText(this,"Added to cart",Toast.LENGTH_LONG).show()
//                }
//                else{
//                    Log.d("TAG", "failed: ${dOrder.code()}")
//                    Toast.makeText(this,"Choose a size and color",Toast.LENGTH_LONG).show()
//                }
//
//            }
        }

    }

    override fun onClickForSelectedColor(color: String) {
        selectedColor = color
        colorAdapter.notifyDataSetChanged()

    }

    override fun onClickForSelectedSize(size: String) {
        selectedSize = size
        sizeAdapter.notifyDataSetChanged()
    }


}