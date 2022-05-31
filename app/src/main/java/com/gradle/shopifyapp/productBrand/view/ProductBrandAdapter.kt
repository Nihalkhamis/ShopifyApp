package com.gradle.shopifyapp.productBrand.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel
import java.util.ArrayList

class ProductBrandAdapter(
    val context: Context,
    private var productsBrand: ArrayList<ProductModel>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductBrandAdapter.ProdcutBrandHolder>() {

    fun setProductsBrand(productsBrandList: List<ProductModel>) {
        this.productsBrand.apply {
            clear()
            addAll(productsBrandList)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdcutBrandHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.wish_list_item, parent, false)
        return ProdcutBrandHolder(view)
    }

    override fun onBindViewHolder(holder: ProdcutBrandHolder, position: Int) {
        holder.product_description_wish_list.text = productsBrand[position].products[position].title
        holder.price_Text_wish_list.text = productsBrand[position].products[position].product_type
        Glide.with(context).load(productsBrand[position].products[position].image.src).into(holder.product_img)
        holder.productCard.setOnClickListener {
            onItemClickListener.onClick(productsBrand[position])
        }
    }

    override fun getItemCount(): Int {
        return productsBrand.count()
    }


    class ProdcutBrandHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product_img: ImageView = itemView.findViewById(R.id.product_img)
        val product_description_wish_list: TextView = itemView.findViewById(R.id.product_description_wish_list)
        val price_Text_wish_list : TextView = itemView.findViewById(R.id.price_Text_wish_list)
        val productCard : LinearLayout = itemView.findViewById(R.id.productCard)
    }
}