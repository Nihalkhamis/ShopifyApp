package com.gradle.shopifyapp.home.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
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

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var slideAdapter:SlideAdapter? = null
    var viewPager: ViewPager? = null
    var clothes = intArrayOf(R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4)
    var ads = intArrayOf(R.drawable.ad2,R.drawable.ad3,R.drawable.ad4)
    var currentPosition: Int = 0

    var couponsAdapter: Coupons_adapter? = null
    var coupons_rv: RecyclerView? = null
    lateinit var gridLayoutManager: GridLayoutManager

    var brandsAdapter: Brands_adapter? = null
    var brands_rv: RecyclerView? = null
    lateinit var gridLayoutManager2: GridLayoutManager



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //ads slideshow
        viewPager = binding.pageView
        slideAdapter = SlideAdapter(requireContext(),ads)
        viewPager!!.adapter = slideAdapter
        createSlideshow()

        //coupons
        coupons_rv = binding.couponsRowRv
        gridLayoutManager = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
        coupons_rv!!.layoutManager = gridLayoutManager
        couponsAdapter = Coupons_adapter(requireContext(),ads)
        couponsAdapter!!.notifyDataSetChanged()
        coupons_rv!!.adapter = couponsAdapter

        //Brands
        brands_rv = binding.brandRowRv
        gridLayoutManager2 = GridLayoutManager(context,1,GridLayoutManager.HORIZONTAL,false)
        brands_rv!!.layoutManager = gridLayoutManager2
        brandsAdapter = Brands_adapter(requireContext(),clothes)
        brandsAdapter!!.notifyDataSetChanged()
        brands_rv!!.adapter = brandsAdapter
        /*
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(yourRecyclerView);
        https://blog.mindorks.com/using-snaphelper-in-recyclerview-fc616b6833e8
         */

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun createSlideshow(){
        var handler: Handler = Handler()
        var runnable: Runnable = Runnable {
            kotlin.run {
                if(currentPosition == clothes.size)
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





}