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
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.authentication.MainActivity
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
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.productdetails.viewmodel.ProductDetailsViewModel
import com.gradle.shopifyapp.productdetails.viewmodel.ProductDetailsViewModelFactory
import com.gradle.shopifyapp.shoppingCart.View.ShoppingCartActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Alert
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

    //for internet connection
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog : android.app.AlertDialog

    var hearted : Boolean = false

    var draftOrderId : Long = 1000

    private lateinit var wishlistFactory: ShoppingCartViewModelFactory
    lateinit var wishlistViewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = MyPreference.getInstance(this)!!

        //for internet connection
        connectionLiveData = ConnectionLiveData(this)
        dialog = Alert.makeAlert(this)

        product = Product()
        vmFactory = ProductDetailsViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )
        productDetailsVm =
            ViewModelProvider(this, vmFactory).get(ProductDetailsViewModel::class.java)

//        //wishlist view model
        wishlistFactory = ShoppingCartViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                this
            ), this
        )

        wishlistViewModel =
            ViewModelProvider(this, wishlistFactory).get(ShoppingCartViewModel::class.java)


        var isFromWishlist = intent.getStringExtra(Constants.FROMWISHLIST)

        Log.d("TAG", "onCreate: $isFromWishlist")
        Log.d("TAG", "onCreate: ${intent.getLongExtra(Constants.SELECTEDPRODUCTID, 1000)}")

        connectionLiveData.observe(this){
            if (it){
                dialog.dismiss()
                //comes from wishlist
                if (isFromWishlist == "true"){
                    Log.d("TAG", "onCreate: FROM WISHLIST")
                    var selectedProductId = intent.getLongExtra(Constants.SELECTEDPRODUCTID,1000)

                    Log.d("TAG", "onCreate: $selectedProductId")

                    productDetailsVm.getProductById(selectedProductId.toString())

                    productDetailsVm.liveDataProductList.observe(this, Observer {
                        Log.d("TAG", "onCreate: ${it.body()}")
                        Log.d("TAG", "onCreate: ${it.body()!!.product}")
                        product = it.body()?.product ?: Product()
                        initUIComponent()

                    })
                }

                // comes from any product
                else{
                    product = intent.getSerializableExtra("product") as Product
                    initUIComponent()

                }

                //check if this product exist in wishlist or not
                wishlistViewModel.getDraftOrder(this)
                wishlistViewModel.liveDraftOrderList.observe(this, Observer {
                    Log.d("TAG", "getFavProducts: ${it.size}")
                    for (i in 0..it.size - 1) {
                        var email = preference.getData(Constants.USEREMAIL)

                        if (it.get(i).email == email && it.get(i).note == "favourite") {
                            if (product.id == it[i].line_items!![0].product_id) {

                                //assign draft order id to this variable to use it in deletion
                                draftOrderId = it[i].id!!

                                binding.favoriteBtn.setImageResource(R.drawable.fav_heart_icon)
                                hearted = true
                                break
                            } else {
                                binding.favoriteBtn.setImageResource(R.drawable.favorite_icon)
                                hearted = false
                            }
                        }
                    }
                    Log.d("TAG", "onCreate: DRAFT ORDER ID-------> $draftOrderId")

                })
            }else{
                dialog.show()
            }
        }


        //back btn
        binding.backBtn.setOnClickListener {
            finish()
        }
        //wishlist
        binding.favoriteBtn.setOnClickListener {
            Log.d("TAG", "onCreate: Fav button clicked !")
            if(preference.getData(Constants.USEREMAIL).isNullOrEmpty()){
                makeAlert()
            }else{
                if (hearted){
                    //remove product from wishlist
                    removeProductFromWishlist(draftOrderId)
//                    hearted = false
//                    binding.favoriteBtn.setImageResource(R.drawable.favorite_icon)
                }
                else{
                    //add product to wishlist
                    addProductToWishlist()
//                    hearted = true
//                    binding.favoriteBtn.setImageResource(R.drawable.fav_heart_icon)
                }
            }

        }

        //cart btn
        binding.cartIcon.setOnClickListener {
            val intent = Intent(this, ShoppingCartActivity::class.java).apply {
            }
            startActivity(intent)
        }

        binding.addToCartBtn.setOnClickListener {
            if(preference.getData(Constants.USEREMAIL).isNullOrEmpty()){
                makeAlert()
            }else{
                if (selectedSize != " " && selectedColor != " ") {
                    var order = DraftOrder()
                    var draft_orders = Draft_order()
                    order.email = preference.getData(Constants.USEREMAIL)
                    order.note = "cart"
                    for (i in 0..product.variants!!.size - 1) {
                        if (product.variants!![i].option1 == selectedSize &&
                            product.variants!![i].option2 == selectedColor
                        ) {
                            var lineItems = LineItem()
                            lineItems.quantity = 1
                            lineItems.variant_id = product.variants!![i].id
                            order.line_items = listOf(lineItems)
                            var note_attribute = NoteAttribute()
                            note_attribute.name = "image"
                            note_attribute.value = product.images!![0].src
                            order.note_attributes = listOf(note_attribute)
                            draft_orders = Draft_order(order)
                            productDetailsVm.postDraftOrder(draft_orders)
                            productDetailsVm.liveDraftOrderList.observe(this) { dOrder ->
                                if (dOrder.isSuccessful) {
                                    Toast.makeText(this, "Added to cart", Toast.LENGTH_LONG).show()
                                } else {
                                    Log.d("TAG", "failed: ${dOrder.code()}")
                                }
                            }
                            break
                        }
                    }
                }
                else{
                    Toast.makeText(this, "You must select size and color first", Toast.LENGTH_LONG).show()
                }
            }

        }


    }
private fun makeAlert(){
    val builder = AlertDialog.Builder(this)
    builder.setTitle("Warning")
    builder.setMessage("Sorry but you have to login at first")
    builder.setNeutralButton("Cancel") { dialog, which -> }
    builder.setPositiveButton("Login"){dialogInterface, which ->
        var intent =Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)

    }

    builder.show()
}
    private fun initUIComponent() {
        // product Images
        viewPager = binding.productViewPager
        dots = binding.dots
        productSliderAdapter = ProductViewPagerAdapter(this, product.images!!)
        viewPager.adapter = productSliderAdapter
        dots.attachViewPager(viewPager)

        //size
        sizeAdapter = SizeRecyclerAdapter(this, product.options!![0].values, this)
       // selectedSize = product.options!![0].values[0]
        selectedSize = " "
        sizeRecyclerView = binding.sizeRecyclerView
        sizeRecyclerView.adapter = sizeAdapter


        //color
        colorAdapter = ColorRecyclerAdapter(this, product.options!![1].values, this)
       // selectedColor = product.options!![1].values[0]
        selectedColor = " "
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
        binding.productPrice.text = String.format("%.2f",(product.variants!![0].price.toDouble() * (preference.getData(Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0))) + "${preference.getData(Constants.TOCURRENCY)}"

        binding.ratingBar.rating = (product.variants!![0].inventory_quantity / 3).toFloat()
    }

    private fun removeProductFromWishlist(id : Long?) {
        Log.d("TAG", "removeProductFromWishlist: $id")
        wishlistViewModel.deleteProductFromDraftOrder(id.toString())
        wishlistViewModel.liveDeleteDraftOrderList.observe(this, Observer{
            if (it.isSuccessful) {
                hearted = false
                binding.favoriteBtn.setImageResource(R.drawable.favorite_icon)
                Toast.makeText(this, "Removed from wishlist", Toast.LENGTH_LONG).show()
            } else {
                Log.d("TAG", "failed: ${it.code()}")
                Log.d("TAG", "onCreate: ${it.errorBody()}")
            }
        })
    }

    private fun addProductToWishlist() {
        var order = DraftOrder()
        // var draft_orders = Draft_order()
        order.email = preference.getData(Constants.USEREMAIL)
        order.note = "favourite"

        var lineItems = LineItem()
        lineItems.quantity = 1
        lineItems.variant_id = product.variants!![0].id

        order.line_items = listOf(lineItems)

        var note_attribute = NoteAttribute()
        note_attribute.name = "image"
        note_attribute.value = product.images!![0].src

        order.note_attributes = listOf(note_attribute)

        var draft_orders = Draft_order(order)

        productDetailsVm.postDraftOrder(draft_orders)

        productDetailsVm.liveDraftOrderList.observe(this, Observer {
            if (it.isSuccessful) {
                hearted = true
                binding.favoriteBtn.setImageResource(R.drawable.fav_heart_icon)
                Toast.makeText(this, "Added to wishlist", Toast.LENGTH_LONG).show()
            } else {
                Log.d("TAG", "failed: ${it.code()}")
                Log.d("TAG", "onCreate: ${it.errorBody()}")
            }
        })
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