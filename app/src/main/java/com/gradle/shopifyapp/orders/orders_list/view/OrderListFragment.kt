package com.gradle.shopifyapp.orders.orders_list.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.me.view.MeFragment
import com.gradle.shopifyapp.me.view.OrderListViewAdapter
import com.gradle.shopifyapp.databinding.FragmentOrderListBinding


class OrderListFragment : Fragment(),OrderOnClickListener {

    private lateinit var constraintLayout: ConstraintLayout


    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    //orders
    lateinit var ordersRecyclerView: RecyclerView
    lateinit var orderRecyclerAdapter: OrderListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)


        //orders
        ordersRecyclerView = binding.ordersRecyclerView

        if(MeFragment.ordersList.isNullOrEmpty()){
            binding.background.setImageResource(R.drawable.orders)

             //binding.orderListFragment.setBackgroundResource(R.drawable.orders)
        }else{
            orderRecyclerAdapter = OrderListViewAdapter(MeFragment.ordersList,this,requireContext())
            Log.i("products",MeFragment.ordersList.toString())
            ordersRecyclerView.adapter=orderRecyclerAdapter
        }



        binding.backBtn.setOnClickListener {
            activity?.finish()
        }
        val root: View = binding.root
        return root
    }


    fun findNavController(fragment: Fragment): NavController? {
        val view = fragment.view
        return Navigation.findNavController(view!!)
    }

    override fun orderOnClickListener(index:Int) {
        var bundel = Bundle()
        bundel.putInt("order_position",index)
        findNavController(this)?.navigate(R.id.fragmentToOrderDetails,bundel)
    }
}