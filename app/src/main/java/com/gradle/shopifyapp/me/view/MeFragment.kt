package com.gradle.shopifyapp.me.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.gradle.shopifyapp.databinding.FragmentMeBinding
import com.gradle.shopifyapp.me.viewmodel.MeViewModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.orders.OrdersActivity
import com.gradle.shopifyapp.orders.orders_list.view.OrderOnClickListener
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModel
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModelFactory
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import com.gradle.shopifyapp.wishlist.view.WishlistActivity


class MeFragment : Fragment(),OrderOnClickListener {

    private var _binding: FragmentMeBinding? = null

    //viewModel
    lateinit var vmFactory: OrderListViewModelFactory
    lateinit var orderListViewModel: OrderListViewModel
    //order
    lateinit var orderRecyclerView : RecyclerView
    lateinit var orderRecyclerAdapter:OrderListViewAdapter
    lateinit var orderLayoutManager: LinearLayoutManager

    companion object{
        lateinit var ordersList:List<com.gradle.shopifyapp.model.OrderModel>
    }


    lateinit var preference: MyPreference


    lateinit var productRecyclerView : RecyclerView
    lateinit var productRecyclerAdapter:ProductRecyclerViewAdapter
    lateinit var productLayoutManager: StaggeredGridLayoutManager
    lateinit var productList:List<Product>


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MeViewModel::class.java)

        _binding = FragmentMeBinding.inflate(inflater, container, false)

        preference = MyPreference.getInstance(requireContext())!!

        // orders
        orderRecyclerView = binding.ordersList
        orderLayoutManager = LinearLayoutManager(context)
        orderRecyclerAdapter = OrderListViewAdapter(ArrayList(),this)
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
        val userId =preference.getData(Constants.USERID)
        orderListViewModel.getOrders(userId!!)

        orderListViewModel.ordersResponseLiveData.observe(viewLifecycleOwner){
            if (it.isSuccessful) {
                Log.i("order Result", it.body()?.orders?.get(0).toString())
                ordersList= it.body()?.orders!!
                if (ordersList.size<=2)
                {
                    orderRecyclerAdapter.orders= ordersList
                }else{
                    orderRecyclerAdapter.orders= ordersList.subList(0,2)
                }
//                orderRecyclerAdapter.orders= ordersList
                orderRecyclerAdapter.notifyDataSetChanged()
            }else{
                Log.i("order Result", it.code().toString())
                Log.i("order Result", "Error")
            }
        }


        //product
        productRecyclerView = binding.listForWishList
        productList = listOf(Product(price = 130.0f,description = "Description for product "),
            Product(price = 60.0f,description = "Description for product oki "),
            Product(price = 87.0f,description = "Description for product "),
                    Product(price = 166.30f,description = "Description for product oki"))
        productLayoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        productRecyclerAdapter = ProductRecyclerViewAdapter(productList)
        productLayoutManager.orientation =RecyclerView.VERTICAL
        productRecyclerView.layoutManager = productLayoutManager
        productRecyclerView.adapter = productRecyclerAdapter

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
//            startActivity(Intent(requireContext(),OrdersListActivity::class.java))
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
}