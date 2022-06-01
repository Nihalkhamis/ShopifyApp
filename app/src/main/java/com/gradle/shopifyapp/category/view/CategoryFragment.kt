package com.gradle.shopifyapp.category.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.databinding.FragmentCategoryBinding
import com.gradle.shopifyapp.model.SubCategoryModel
import com.gradle.shopifyapp.productBrand.view.ProductBrandActivity

class CategoryFragment : Fragment(), TabLayout.OnTabSelectedListener, OnItemClickListener {

    private var _binding: FragmentCategoryBinding? = null

    private val binding get() = _binding!!


    lateinit var subCategoryAdapter: SubCategoryAdapter
    lateinit var subCategoriesList : ArrayList<SubCategoryModel>


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(CategoryViewModel::class.java)


        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root

//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.women))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.men))
       binding.categoryTabLayout.addTab( binding.categoryTabLayout.newTab().setText(R.string.kids))

        binding.categoryTabLayout.addOnTabSelectedListener(this)

       setAdapter()

    }

    fun setAdapter(){
        subCategoriesList = ArrayList<SubCategoryModel>()


        subCategoryAdapter = SubCategoryAdapter(requireContext(), ArrayList(),this)
        binding.subCategoryRV.adapter = subCategoryAdapter

        setWomenCategory()
    }

    private fun setWomenCategory() {
        subCategoriesList.clear()
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Dresses", R.drawable.dress))
        subCategoryAdapter.setSubCategories(subCategoriesList)

    }


    private fun setMenCategory() {
        subCategoriesList.clear()
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Pants", R.drawable.dress))
        subCategoryAdapter.setSubCategories(subCategoriesList)

    }


    private fun setKidsCategory() {
        subCategoriesList.clear()
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoriesList.add(SubCategoryModel("Shorts", R.drawable.dress))
        subCategoryAdapter.setSubCategories(subCategoriesList)

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

    override fun onClick(subCategoryModel: SubCategoryModel) {
//        var intent = Intent(requireContext(),ProductBrandActivity::class.java)
//        startActivity(intent)
    }
}