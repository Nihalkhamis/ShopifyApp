package com.gradle.shopifyapp.category.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.SubCategoryModel
import java.util.ArrayList

class SubCategoryAdapter(
    val context: Context,
    private var subCategories: ArrayList<SubCategoryModel>,
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
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return SubCategoryHolder(view)
    }

    override fun onBindViewHolder(holder: SubCategoryHolder, position: Int) {
        holder.product_description_wish_list.text = subCategories[position].subcategoryName
        holder.price_Text_wish_list.text = subCategories[position].price.toString()
        holder.product_img.setImageResource(R.drawable.dress)

        holder.productCard.setOnClickListener {
           // onItemClickListener.onClick(subCategories[position])
        }
    }

    override fun getItemCount(): Int {
        return subCategories.count()
    }


    class SubCategoryHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product_img: ImageView = itemView.findViewById(R.id.product_img)
        val product_description_wish_list: TextView = itemView.findViewById(R.id.product_description_wish_list)
        val price_Text_wish_list : TextView = itemView.findViewById(R.id.price_Text_wish_list)
        val productCard : CardView = itemView.findViewById(R.id.productCard)
    }
}