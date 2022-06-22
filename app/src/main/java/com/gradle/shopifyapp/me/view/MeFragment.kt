package com.gradle.shopifyapp.me.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gradle.shopifyapp.databinding.FragmentMeBinding
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.LineItem
import com.gradle.shopifyapp.me.viewmodel.MeViewModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.orders.OrdersActivity
import com.gradle.shopifyapp.orders.orders_list.view.OrderOnClickListener
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModel
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModelFactory
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModel
import com.gradle.shopifyapp.shoppingCart.viewmodel.ShoppingCartViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import com.gradle.shopifyapp.wishlist.view.WishlistActivity
import com.gradle.shopifyapp.wishlist.view.WishlistAdapter


class MeFragment : Fragment(),OrderOnClickListener,OnWishListItemClick {

    private var _binding: FragmentMeBinding? = null

    //viewModel
    lateinit var vmFactory: OrderListViewModelFactory
    lateinit var orderListViewModel: OrderListViewModel
    //order
    lateinit var orderRecyclerView : RecyclerView
    lateinit var orderRecyclerAdapter:OrderListViewAdapter
    lateinit var orderLayoutManager: LinearLayoutManager
    //for wishList
    private lateinit var wishListVmFactory: ShoppingCartViewModelFactory
    lateinit var wishListviewModel: ShoppingCartViewModel
    var favProducts: ArrayList<Draft_order> = ArrayList<Draft_order>()
    var lineItems = ArrayList<LineItem>()


    companion object{
        lateinit var ordersList:List<com.gradle.shopifyapp.model.OrderModel>
    }


    lateinit var preference: MyPreference


    lateinit var productRecyclerView : RecyclerView
    lateinit var productRecyclerAdapter:ProductRecyclerViewAdapter
    lateinit var productLayoutManager: StaggeredGridLayoutManager



    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMeBinding.inflate(inflater, container, false)
        preference = MyPreference.getInstance(requireContext())!!

        // orders
        orderRecyclerView = binding.ordersList
        orderLayoutManager = LinearLayoutManager(context)
        orderRecyclerAdapter = OrderListViewAdapter(ArrayList(),this,requireContext())
        orderLayoutManager.orientation =RecyclerView.VERTICAL
        orderRecyclerView.layoutManager = orderLayoutManager
        orderRecyclerView.adapter = orderRecyclerAdapter


        //viewModel
        vmFactory = OrderListViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            )
        )
        orderListViewModel = ViewModelProvider(this, vmFactory).get(OrderListViewModel::class.java)

        //wishList Product
        wishListVmFactory = ShoppingCartViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )
        wishListviewModel = ViewModelProvider(this, wishListVmFactory).get(ShoppingCartViewModel::class.java)



        if(preference.getData(Constants.USEREMAIL).isNullOrEmpty()){
            binding.moreForOrders.visibility=View.GONE
            binding.moreForWishList.visibility=View.GONE
            binding.welcome.visibility =View.GONE
            binding.ordersText.visibility = View.GONE
            binding.wishListText.visibility = View.GONE
            binding.noUser.text = "You have to login at first"
        }else{
                val userId =preference.getData(Constants.USERID)
                orderListViewModel.getOrders(userId!!)
                orderListViewModel.ordersResponseLiveData.observe(viewLifecycleOwner){
                    if (it.isSuccessful) {
                        ordersList= it.body()?.orders!!
                        if (!ordersList.isNullOrEmpty()){
                            if (ordersList.size<=2)
                            {
                                orderRecyclerAdapter.orders= ordersList
                            }else{
                                orderRecyclerAdapter.orders= ordersList.subList(0,2)
                            }
                        }
                        else{
                            binding.noOrders.visibility=View.VISIBLE
                        }

                        orderRecyclerAdapter.notifyDataSetChanged()
                    }else{
                        Log.i("order Result", it.code().toString())
                        Log.i("order Result", "Error")
                    }
                }


        }








        binding.settingsImg.setOnClickListener {
            var intent = Intent(requireContext(), SettingsActivity::class.java)
            startActivity(intent)
        }
        binding.moreForWishList.text = Html.fromHtml("<u>more</u>")
        binding.moreForWishList.setOnClickListener {
            startActivity(Intent(requireContext(),WishlistActivity::class.java))
        }




        binding.moreForOrders.text = Html.fromHtml("<u>more</u>")
        binding.moreForOrders.setOnClickListener {
            startActivity(Intent(requireContext(),OrdersActivity::class.java))
        }

       val userName = preference.getData(Constants.USERFIRSTNAME)
        if (!userName.isNullOrEmpty()){
            binding.welcome.text = "Welcome $userName"
        }


        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun orderOnClickListener(index:Int) {
    }


    private fun getFavProducts(){
        productRecyclerAdapter = ProductRecyclerViewAdapter(arrayListOf(),requireContext(),this)

        wishListviewModel.getDraftOrder(requireContext())
        wishListviewModel.liveDraftOrderList.observe(viewLifecycleOwner) {
            favProducts.clear()
//            Log.d("TAG", "getFavProducts: ${it.size}")
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
            productRecyclerView = binding.listForWishList
            productLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

           if (!favProducts.isNullOrEmpty()){
               if (favProducts.size<=2)
               {
                   orderRecyclerAdapter.orders= ordersList
                   productRecyclerAdapter.favProducts=favProducts


               }else{
                   productRecyclerAdapter.favProducts=favProducts.subList(0,4)
               }
           }else{
               binding.noFavorite.visibility=View.VISIBLE
           }

            productLayoutManager.orientation =RecyclerView.VERTICAL
            productRecyclerView.layoutManager = productLayoutManager
            productRecyclerView.adapter = productRecyclerAdapter

            //  wishlistAdapter.setFavProducts(favProducts)
        }


        wishListviewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding!!.progressbar.visibility = View.VISIBLE
            } else {
                binding!!.progressbar.visibility = View.GONE
            }
        })
    }

    override fun onClick(draftOrder: Draft_order) {
        Log.d("TAG", "onClickProduct: ${draftOrder.draft_order?.line_items?.get(0)?.product_id}")
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra(Constants.SELECTEDPRODUCTID, draftOrder.draft_order?.line_items?.get(0)?.product_id)
        intent.putExtra(Constants.FROMWISHLIST,"true")

        Log.d("TAG", "onClickProduct: ${intent.getLongExtra(Constants.SELECTEDPRODUCTID,1000)}")
        Log.d("TAG", "onClickProduct: ${intent.getStringExtra(Constants.FROMWISHLIST)}")

        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        if(preference.getData(Constants.USEREMAIL).isNullOrEmpty()){
            binding.moreForOrders.visibility=View.GONE
            binding.moreForWishList.visibility=View.GONE
            binding.welcome.visibility =View.GONE
            binding.ordersText.visibility = View.GONE
            binding.wishListText.visibility = View.GONE
            binding.noUser.text = "You have to login at first"
            //binding.settingsImg.visibility = View.GONE
        }else{
                getFavProducts()

        }
    }
}