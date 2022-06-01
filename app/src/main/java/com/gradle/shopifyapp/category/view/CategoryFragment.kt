package com.gradle.shopifyapp.category.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.category.viewmodel.CategoryViewModel
import com.gradle.shopifyapp.category.viewmodel.CategoryViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentCategoryBinding
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.productBrand.view.OnItemClickListener
import com.gradle.shopifyapp.productBrand.view.ProductBrandAdapter


class CategoryFragment : Fragment(), TabLayout.OnTabSelectedListener, OnItemClickListener {

    private var _binding: FragmentCategoryBinding? = null

    private val binding get() = _binding!!

    lateinit var productBrandAdapter: ProductBrandAdapter
    lateinit var vmFactory: CategoryViewModelFactory
    lateinit var categoryViewModel: CategoryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.women))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.men))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.kids))

        binding.categoryTabLayout.addOnTabSelectedListener(this)

        vmFactory = CategoryViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )

        // 273053745291 -> kid
        // 273053679755 -> men
        // 273053712523 -> women

        categoryViewModel = ViewModelProvider(this, vmFactory).get(CategoryViewModel::class.java)
        setAdapter()

    }

    private fun setAdapter(){

        productBrandAdapter = ProductBrandAdapter(requireContext(), ArrayList(), this)
        binding.subCategoryRV.adapter = productBrandAdapter

        setWomenCategory()

        categoryViewModel.liveDataCategoriesProductList.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreateView: $it")
            productBrandAdapter.setProductsBrand(it.products)
        }

    }

    private fun setWomenCategory() {
        categoryViewModel.getAllCategoriesProducts(requireContext(),"273053712523")
    }


    private fun setMenCategory() {
        categoryViewModel.getAllCategoriesProducts(requireContext(),"273053679755")

    }


    private fun setKidsCategory() {
        categoryViewModel.getAllCategoriesProducts(requireContext(),"273053745291")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when(tab?.position){
            0->{setWomenCategory()}
            1->{setMenCategory()}
            2->{setKidsCategory()}
        }
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
    }

    override fun onClick(productModel: Product) {

    }


}