package com.gradle.shopifyapp.home.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.afollestad.viewpagerdots.DotsIndicator
import com.google.android.material.snackbar.Snackbar
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentHomeBinding
import com.gradle.shopifyapp.home.viewmodel.HomeViewModel
import com.gradle.shopifyapp.home.viewmodel.HomeViewModelFactory
import com.gradle.shopifyapp.model.*
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.InternetConnection
import com.gradle.shopifyapp.productBrand.view.ProductBrandActivity
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class HomeFragment : Fragment(), OnBrandClickListener {

    private var _binding: FragmentHomeBinding? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    companion object {
        var myProducts: List<Product>? = null

    }

    private val binding get() = _binding!!
    var slideAdapter: SlideAdapter? = null
    var viewPager: ViewPager? = null
    var ads = intArrayOf(R.drawable.nike, R.drawable.adidas, R.drawable.converse)
    var currentPosition: Int = 0
    lateinit var dots: DotsIndicator

    lateinit var couponsAdapter: Coupons_adapter
    private lateinit var coupons_rv: RecyclerView
    lateinit var gridLayoutManager: GridLayoutManager

    lateinit var brandsAdapter: Brands_adapter
    private lateinit var brands_rv: RecyclerView

    //viewModel
    lateinit var vmFactory: HomeViewModelFactory
    private lateinit var homeViewModel: HomeViewModel

    lateinit var preference: MyPreference


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
        dots = binding.dots
        dots.attachViewPager(viewPager)
        createSlideshow()


        vmFactory = HomeViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        preference = MyPreference.getInstance(requireContext())!!

        //check for the connection at first
       if (InternetConnection.isInternetAvailable(requireContext())){
           binding.withInterNetConnection.visibility = View.VISIBLE
           binding.noInternet.visibility = View.GONE
           homeViewModel = ViewModelProvider(this, vmFactory).get(HomeViewModel::class.java)

           homeViewModel.getAllProducts(requireContext(), "", "", "")
           homeViewModel.liveDataProductList.observe(viewLifecycleOwner) {
               myProducts = it.products
           }

           homeViewModel.getAllVendors(requireContext())
           homeViewModel.liveVendorList.observe(viewLifecycleOwner) {
               bindBrands(it)
           }

           homeViewModel.getAllDiscountCodes(requireContext())
           homeViewModel.liveDiscountList.observe(viewLifecycleOwner) {
               bindCoupons(it)
           }

           // currency converter
           homeViewModel.getAllConvertedCurrency(
               requireContext(), "1",
               "EGP", preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY, "EGP")!!
           )
           homeViewModel.liveDataConvertCurrencyList.observe(viewLifecycleOwner) {
               Log.d("TAG", "onCreateView: IT-> $it")
               preference.saveData(Constants.CURRENCYRESULT, it.toString())
               Log.d(
                   "TAG",
                   "onCreateView: CURRENCY RESULT-> ${preference.getData(Constants.CURRENCYRESULT)}"
               )
           }
           homeViewModel.loading.observe(viewLifecycleOwner, Observer {
               if (it) {
                   binding.progressbar.visibility = View.VISIBLE
               } else {
                   binding.progressbar.visibility = View.GONE
               }
           })


           //Brands
           brands_rv = binding.brandRowRv
           brandsAdapter = Brands_adapter(requireContext(), this)
           brands_rv.adapter = brandsAdapter

           //coupons
           coupons_rv = binding.couponsRowRv
           gridLayoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
           coupons_rv!!.layoutManager = gridLayoutManager
           couponsAdapter = Coupons_adapter(requireContext(),this)
           coupons_rv!!.adapter = couponsAdapter
       }else{
           binding.withInterNetConnection.visibility = View.GONE
           binding.noInternet.visibility = View.VISIBLE
       }

        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createSlideshow() {
        var handler: Handler = Handler()
        var runnable: Runnable = Runnable {
            kotlin.run {
                if (currentPosition == ads.size)
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

    private fun bindBrands(vendors: VendorsModel) {
        brandsAdapter.setBrands(vendors.smart_collections)
    }

    override fun onClick(smartCollection: SmartCollection) {
        var intent = Intent(requireContext(), ProductBrandActivity::class.java)
        intent.putExtra(Constants.BRANDID, smartCollection.id.toString())
        intent.putExtra(Constants.BRANDNAME, smartCollection.title)
        startActivity(intent)
    }

    override fun onCouponClickListener(text: String) {
        Snackbar.make(requireView(),text,Snackbar.LENGTH_SHORT).show()
    }

    private fun bindCoupons(coupons: DiscountCodeModel) {
        couponsAdapter.setCoupons(coupons.discount_codes)
    }


}
