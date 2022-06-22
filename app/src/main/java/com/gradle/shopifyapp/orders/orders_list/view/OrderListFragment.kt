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
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.me.view.MeFragment
import com.gradle.shopifyapp.me.view.OrderListViewAdapter
import com.gradle.shopifyapp.databinding.FragmentOrderListBinding
import com.gradle.shopifyapp.network.ConnectionLiveData


class OrderListFragment : Fragment(),OrderOnClickListener {

    private var _binding: FragmentOrderListBinding? = null
    private val binding get() = _binding!!
    //orders
    lateinit var ordersRecyclerView: RecyclerView
    lateinit var orderRecyclerAdapter: OrderListViewAdapter

    lateinit var connectionLiveData: ConnectionLiveData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderListBinding.inflate(inflater, container, false)


        //orders
        ordersRecyclerView = binding.ordersRecyclerView

        if(MeFragment.ordersList.isNullOrEmpty()){
            binding.background.setImageResource(R.drawable.orders)

        }else{
            orderRecyclerAdapter = OrderListViewAdapter(MeFragment.ordersList,this,requireContext())
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
    private fun showSnackBar(msg:String){
        val snackBar = Snackbar.make(requireActivity().findViewById(android.R.id.content),
            msg, Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }


    override fun onStart() {
        super.onStart()
        var  firstTime = true
        connectionLiveData = ConnectionLiveData(requireContext())
        connectionLiveData.observe(viewLifecycleOwner){
            if (it){
                // for not making it at the first time when entre the activity
                if(!firstTime){
                    showSnackBar("We back online")

                }else{
                    firstTime = false
                }

            }else{
                showSnackBar("Be careful we lost the connection")

            }
        }
    }
}