package com.gradle.shopifyapp.home.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentHomeBinding
import com.gradle.shopifyapp.home.viewmodel.HomeViewModel
import com.gradle.shopifyapp.home.viewmodel.HomeViewModelFactory
import com.gradle.shopifyapp.model.DiscountCodeModel
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var slideAdapter:SlideAdapter? = null
    var viewPager: ViewPager? = null
<<<<<<< Updated upstream
    var clothes = intArrayOf(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4)
    var ads = intArrayOf(R.drawable.ad2,R.drawable.ad3,R.drawable.ad4)
=======
    //var clothes = intArrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4)
    var ads = intArrayOf(R.drawable.nike, R.drawable.adidas, R.drawable.converse)
>>>>>>> Stashed changes
    var currentPosition: Int = 0

    lateinit var couponsAdapter: Coupons_adapter
    lateinit var coupons_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

<<<<<<< Updated upstream
    var brandsAdapter: Brands_adapter? = null
    var brands_rv: RecyclerView? = null
    lateinit var gridLayoutManager2: GridLayoutManager
=======
    lateinit var brandsAdapter: Brands_adapter
    lateinit var brands_rv: RecyclerView
>>>>>>> Stashed changes

    //viewModel
    lateinit var vmFactory: HomeViewModelFactory
    lateinit var homeViewModel : HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //ads slideshow
        viewPager = binding.pageView
        slideAdapter = SlideAdapter(requireContext(),ads)
        viewPager!!.adapter = slideAdapter
        createSlideshow()

<<<<<<< Updated upstream
        //coupons
        coupons_rv = binding.couponsRowRv
        gridLayoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
        coupons_rv!!.layoutManager = gridLayoutManager
        couponsAdapter = Coupons_adapter(requireContext(),ads)
        couponsAdapter!!.notifyDataSetChanged()
        coupons_rv!!.adapter = couponsAdapter
=======
>>>>>>> Stashed changes

        //Brands
        brands_rv = binding.brandRowRv
        brandsAdapter = Brands_adapter(requireContext(),clothes)
        brandsAdapter!!.notifyDataSetChanged()
        brands_rv!!.adapter = brandsAdapter


        vmFactory = HomeViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ),requireContext()
        )


<<<<<<< Updated upstream

        homeViewModel = ViewModelProvider(this,vmFactory).get(HomeViewModel::class.java)
        homeViewModel.getAllProducts(requireContext())

        homeViewModel.liveDataProductList.observe(viewLifecycleOwner){
            Log.d("TAG", "onCreateView: ${it.products}")
        }

=======
        homeViewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)

        homeViewModel.getAllProducts(requireContext())
        homeViewModel.liveDataProductList.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: ${it.products}")
        }

        homeViewModel.getAllVendors(requireContext())
        homeViewModel.liveVendorList.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: ${it.smart_collections}")
            bindBrands(it)
        }

        homeViewModel.getAllDiscountCodes(requireContext())
        homeViewModel.liveDiscountList.observe(viewLifecycleOwner){
            Log.d("TAG", "onCreateView: ${it}")
            bindCoupons(it)
        }
>>>>>>> Stashed changes

        return root
    }

<<<<<<< Updated upstream
=======
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Brands
        brands_rv = binding.brandRowRv
        brandsAdapter = Brands_adapter(requireContext())
        brands_rv.adapter = brandsAdapter

        //coupons
        coupons_rv = binding.couponsRowRv
        gridLayoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        coupons_rv!!.layoutManager = gridLayoutManager
        couponsAdapter = Coupons_adapter(requireContext())
        coupons_rv!!.adapter = couponsAdapter

    }

>>>>>>> Stashed changes
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createSlideshow(){
        var handler: Handler = Handler()
        var runnable: Runnable = Runnable {
            kotlin.run {
<<<<<<< Updated upstream
                if(currentPosition == clothes.size)
=======
                if (currentPosition == ads.size)
>>>>>>> Stashed changes
                    currentPosition = 0
                viewPager!!.setCurrentItem(currentPosition++,true)

            }
        }
        object : CountDownTimer(30000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                handler.post(runnable)
            }

            override fun onFinish() {
               print("done")
            }
        }.start()
    }




    private fun bindCoupons(coupons: DiscountCodeModel){
        couponsAdapter.setCoupons(coupons.discount_codes)
    }


}