package com.gradle.shopifyapp.category.view

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.category.viewmodel.CategoryViewModel
import com.gradle.shopifyapp.category.viewmodel.CategoryViewModelFactory
import com.gradle.shopifyapp.databinding.FragmentCategoryBinding
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.Repository
import com.gradle.shopifyapp.network.ApiClient
import com.gradle.shopifyapp.network.ConnectionLiveData
import com.gradle.shopifyapp.productBrand.view.OnItemClickListener
import com.gradle.shopifyapp.productBrand.view.ProductBrandAdapter
import com.gradle.shopifyapp.productdetails.views.ProductDetailsActivity
import com.gradle.shopifyapp.utils.Alert
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference


class CategoryFragment : Fragment(), TabLayout.OnTabSelectedListener, OnItemClickListener, OnProductTypeItemListener {

    private var _binding: FragmentCategoryBinding? = null

    private val binding get() = _binding!!

    //for internet connection
    lateinit var connectionLiveData: ConnectionLiveData
    lateinit var dialog :AlertDialog



    lateinit var productBrandAdapter: ProductBrandAdapter
    lateinit var vmFactory: CategoryViewModelFactory
    lateinit var categoryViewModel: CategoryViewModel

    lateinit var productTypeAdapter: ProductTypeAdapter

    lateinit var productTypeList : MutableSet<String>

    lateinit var categoryId : String

    lateinit var preference : MyPreference

    lateinit var currencyConvertedList : ArrayList<Product>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCategoryBinding.inflate(inflater, container, false)

        //for internet connection
        connectionLiveData = ConnectionLiveData(requireContext())
        dialog = Alert.makeAlert(requireContext())


        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.women))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.men))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.kids))

        binding.categoryTabLayout.addOnTabSelectedListener(this)

        preference = MyPreference.getInstance(requireContext())!!

        currencyConvertedList = ArrayList<Product>()


        vmFactory = CategoryViewModelFactory(
            Repository.getRepoInstance(
                ApiClient.getClientInstance()!!,
                requireContext()
            ), requireContext()
        )


        categoryViewModel = ViewModelProvider(this, vmFactory).get(CategoryViewModel::class.java)

        categoryId = "273053712523"
        setAdapter()
        connectionLiveData.observe(viewLifecycleOwner){
                isNetworkAvaliable->
            if (isNetworkAvaliable){
                dialog.dismiss()

            }else{
                dialog.show()
            }
        }

    }

    private fun setAdapter(){

        productBrandAdapter = ProductBrandAdapter(requireContext(), ArrayList(), this)
        binding.subCategoryRV.adapter = productBrandAdapter

        productTypeAdapter = ProductTypeAdapter(requireContext(), ArrayList(), this)
        binding.productTypeRV.adapter = productTypeAdapter

        productTypeList = mutableSetOf()

        setWomenCategory()

        categoryViewModel.liveDataCategoriesProductList.observe(viewLifecycleOwner) {

            productBrandAdapter.setProductsBrand(it.products)
            for (productType in it.products){
                 productTypeList.add(productType.product_type!!)
            }
            productTypeAdapter.setProductTypes(productTypeList.toList())
        }

        categoryViewModel.loading.observe(viewLifecycleOwner, Observer {
            if (it) {
                binding.progressbar.visibility = View.VISIBLE
            } else {
                binding.progressbar.visibility = View.GONE
            }
        })

    }

    private fun setWomenCategory() {
        categoryId = "274500059275"
        connectionLiveData.observe(viewLifecycleOwner){
                isNetworkAvaliable->
            if (isNetworkAvaliable){
                dialog.dismiss()
                productBrandAdapter.deleteProductBrand()
                productTypeAdapter.deleteProductTypes()
                // delete old products to fetch new ones
                productTypeList.clear()
                categoryViewModel.getAllCategoriesProducts(requireContext(), categoryId,"","")
            }else{
                dialog.show()
            }
        }

    }


    private fun setMenCategory() {
        categoryId = "274500026507"

        connectionLiveData.observe(viewLifecycleOwner){
                isNetworkAvaliable->
            if (isNetworkAvaliable){
                dialog.dismiss()
                productBrandAdapter.deleteProductBrand()
                productTypeAdapter.deleteProductTypes()
                productTypeList.clear()
                categoryViewModel.getAllCategoriesProducts(requireContext(), categoryId,"", "")
            }else{
                dialog.show()
            }
        }

    }


    private fun setKidsCategory() {
        categoryId = "274500092043"
        connectionLiveData.observe(viewLifecycleOwner){
                isNetworkAvaliable->
            if (isNetworkAvaliable){
                dialog.dismiss()
                productBrandAdapter.deleteProductBrand()
                productTypeAdapter.deleteProductTypes()
                productTypeList.clear()
                categoryViewModel.getAllCategoriesProducts(requireContext(),categoryId,"","")
            }else{
                dialog.show()
            }
        }



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

    override fun onClick(productModel: Product,price:String) {
        val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
        intent.putExtra("product",productModel)
        intent.putExtra("price",price)
        startActivity(intent)
    }

    override fun onClick(productTypeName: String, isFiltered: Boolean) {
         //if isFiltered == true so there is a filtration, if false there is not
        if (isFiltered){
            categoryViewModel.getAllCategoriesProducts(requireContext(), categoryId, productTypeName,"")
        }
        else{
            categoryViewModel.getAllCategoriesProducts(requireContext(), categoryId, "","")
        }


    }


}