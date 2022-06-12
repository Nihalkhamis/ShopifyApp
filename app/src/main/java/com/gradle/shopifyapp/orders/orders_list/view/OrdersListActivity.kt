package com.gradle.shopifyapp.orders.orders_list.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.gradle.shopifyapp.databinding.ActivityOrdersListBinding
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModel
import com.gradle.shopifyapp.orders.orders_list.viewmodel.OrderListViewModelFactory
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class OrdersListActivity : AppCompatActivity() {
    lateinit var binding: ActivityOrdersListBinding
    lateinit var preference: MyPreference

    //viewModel
    lateinit var vmFactory: OrderListViewModelFactory
    lateinit var orderListViewModel: OrderListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrdersListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preference = MyPreference.getInstance(this)!!

        //viewModel
        vmFactory = OrderListViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
               this
            )
        )
        orderListViewModel = ViewModelProvider(this, vmFactory).get(OrderListViewModel::class.java)
        val userId =preference.getData(Constants.USERID)
        orderListViewModel.getOrders(userId!!)

        orderListViewModel.ordersResponseLiveData.observe(this){
            if (it.isSuccessful) {
                    Log.i("order Result", it.body()?.orders?.get(0).toString())
            }else{
                Log.i("order Result", it.code().toString())
                Log.i("order Result", "Error")
            }
        }
}
}