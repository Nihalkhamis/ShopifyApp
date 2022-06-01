package com.gradle.shopifyapp.home.view

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.provider.SyncStateContract
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
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.model.SmartCollection
import com.gradle.shopifyapp.model.VendorsModel
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productBrand.view.ProductBrandActivity
import com.kotlin.weatherforecast.utils.Constants

class HomeFragment : Fragment(), OnBrandClickListener {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    companion object{
        var myProducts:List<Product>? = null

    }

    private val binding get() = _binding!!
    var slideAdapter: SlideAdapter? = null
    var viewPager: ViewPager? = null
    var clothes = intArrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4)
    var ads = intArrayOf(R.drawable.ad2, R.drawable.ad3, R.drawable.ad4)
    var currentPosition: Int = 0

    var couponsAdapter: Coupons_adapter? = null
    var coupons_rv: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var brandsAdapter: Brands_adapter
    lateinit var brands_rv: RecyclerView
    lateinit var gridLayoutManager2: GridLayoutManager

    //viewModel
    lateinit var vmFactory: HomeViewModelFactory
    lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //ads slideshow
        viewPager = binding.pageView
        slideAdapter = SlideAdapter(requireContext(), ads)
        viewPager!!.adapter = slideAdapter
        createSlideshow()

        //coupons
        coupons_rv = binding.couponsRowRv
        gridLayoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
        coupons_rv!!.layoutManager = gridLayoutManager
        couponsAdapter = Coupons_adapter(requireContext(), ads)
        couponsAdapter!!.notifyDataSetChanged()
        coupons_rv!!.adapter = couponsAdapter

        vmFactory = HomeViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )


        homeViewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)
        homeViewModel.getAllProducts(requireContext())

        homeViewModel.liveDataProductList.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: ${it.products}")
            myProducts =it.products
        }

        homeViewModel.getAllVendors(requireContext())

        homeViewModel.liveVendorList.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: ${it.smart_collections}")
            bindBrands(it)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Brands
        brands_rv = binding.brandRowRv
        brandsAdapter = Brands_adapter(requireContext(),this)
//        brandsAdapter!!.notifyDataSetChanged()
        brands_rv.adapter = brandsAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createSlideshow() {
        var handler: Handler = Handler()
        var runnable: Runnable = Runnable {
            kotlin.run {
                if (currentPosition == clothes.size)
                    currentPosition = 0
                viewPager!!.setCurrentItem(currentPosition++, true)

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

    private fun bindBrands(vendors: VendorsModel){
        brandsAdapter.setBrands(vendors.smart_collections)
    }

    override fun onClick(smartCollection: SmartCollection) {
        Log.d("TAG", "onBrandClick: ${smartCollection.id}")
        var intent = Intent(requireContext(), ProductBrandActivity::class.java)
        intent.putExtra(Constants.BRANDID,smartCollection.id.toString())
        intent.putExtra(Constants.BRANDNAME,smartCollection.title)
        startActivity(intent)
    }


}