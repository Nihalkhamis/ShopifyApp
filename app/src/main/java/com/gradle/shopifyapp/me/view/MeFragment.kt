package com.gradle.shopifyapp.me.view

import android.content.Intent
import android.os.Bundle
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
import com.gradle.shopifyapp.settings.SettingsActivity
import com.gradle.shopifyapp.wishlist.view.WishlistActivity

class MeFragment : Fragment() {

    private var _binding: FragmentMeBinding? = null

    lateinit var orderRecyclerView : RecyclerView
    lateinit var orderRecyclerAdapter:OrderListViewAdapter
    lateinit var orderLayoutManager: LinearLayoutManager
    lateinit var orderList:List<OrderModel>


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
        orderRecyclerView = binding.ordersList
        orderList =  listOf(OrderModel(date = "12/5/2022",price = 20.0f),
        OrderModel(date = "20/3/2019",price = 290.0f)
         )

        orderLayoutManager = LinearLayoutManager(context)
        orderRecyclerAdapter = OrderListViewAdapter(orderList)
        orderLayoutManager.orientation =RecyclerView.VERTICAL
        orderRecyclerView.layoutManager = orderLayoutManager
        orderRecyclerView.adapter = orderRecyclerAdapter

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
        binding.moreForWishList.setOnClickListener {
            startActivity(Intent(requireContext(),WishlistActivity::class.java))
        }


        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}