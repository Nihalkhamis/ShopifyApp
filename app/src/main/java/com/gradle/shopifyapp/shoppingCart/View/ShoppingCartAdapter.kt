package com.gradle.shopifyapp.shoppingCart.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R

class ShoppingCartAdapter(var context: Context, var shoppingCartItems: IntArray): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.shoppingCartImg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(shoppingCartItems[position]).apply(
            RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
        ).into(holder.img)
    }

    override fun getItemCount(): Int {
        return shoppingCartItems.size
    }
}