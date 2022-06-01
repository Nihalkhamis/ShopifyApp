package com.gradle.shopifyapp.me.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R

class ProductRecyclerViewAdapter(var products : List<Product>) : RecyclerView.Adapter<ProductRecyclerViewAdapter.Holder>() {
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view: View =itemView
        var productImg:ImageView = view.findViewById(R.id.product_img)
        var favoriteImage: ImageView = view.findViewById(R.id.favorite_img)
        var priceText : TextView = view.findViewById(R.id.price_Text_wish_list)
        var descriptionText : TextView = view.findViewById(R.id.product_description_wish_list)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductRecyclerViewAdapter.Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.priceText.text = products[position].price.toString()
        holder.descriptionText.text = products[position].description
        holder.productImg.setImageResource(R.drawable.cartoon)

    }

    override fun getItemCount(): Int {
      return products.count()
    }


}