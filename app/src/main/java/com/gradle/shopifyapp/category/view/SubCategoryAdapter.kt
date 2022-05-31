package com.gradle.shopifyapp.category.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.SubCategoryModel
import java.util.ArrayList

class SubCategoryAdapter(
    val context: Context,
    private var subCategories: ArrayList<SubCategoryModel>,
    val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<SubCategoryAdapter.SubCategoryHolder>() {

    fun setSubCategories(subCategoriesList: List<SubCategoryModel>) {
        this.subCategories.apply {
            //weathers = weatherList
            clear()
            addAll(subCategoriesList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubCategoryHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.sub_category_item, parent, false)
        return SubCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        holder.categoryTxt.text = subCategories[position].subcategoryName
        holder.categoryImg.setImageResource(R.drawable.dress)
        holder.subCategory_layout.setOnClickListener {
            onItemClickListener.onClick(subCategories[position])
        }
    }

    override fun getItemCount(): Int {
        return subCategories.count()
    }


    class SubCategoryHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryImg: ImageView = itemView.findViewById(R.id.categoryImg)
        val categoryTxt: TextView = itemView.findViewById(R.id.categoryTxt)
        val subCategory_layout: ConstraintLayout = itemView.findViewById(R.id.subCategory_layout)
    }
}