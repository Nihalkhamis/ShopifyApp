package com.gradle.shopifyapp.productBrand.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel
import java.util.ArrayList

class ProductBrandAdapter(
    val context: Context,
    private var productsBrand: ArrayList<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductBrandAdapter.ProdcutBrandHolder>() {

    fun setProductsBrand(productsBrandList: List<Product>) {
        this.productsBrand.apply {
            clear()
            addAll(productsBrandList)
            notifyDataSetChanged()
        }
    }

    fun deleteProductBrand(){
        this.productsBrand.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdcutBrandHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProdcutBrandHolder(view)
    }

    override fun onBindViewHolder(holder: ProdcutBrandHolder, position: Int) {
        holder.product_description_wish_list.text = productsBrand[position].title
        holder.price_Text_wish_list.text = productsBrand[position].variants[0].price
        Glide.with(context).load(productsBrand[position].image.src).into(holder.product_img)
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
        val productCard : CardView = itemView.findViewById(R.id.productCard)
    }
}