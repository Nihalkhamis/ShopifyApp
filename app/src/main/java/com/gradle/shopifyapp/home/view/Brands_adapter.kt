package com.gradle.shopifyapp.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R

class Brands_adapter(var context: Context, var brands: IntArray): RecyclerView.Adapter<Brands_adapter.ViewHolder>() {

    // private var coupons = emptyList<IntArray>()

//    fun setCoupons(coupons: IntArray) {
//        this.coupons = listOf(coupons)
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.brand_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.brands_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(brands[position]).apply(
            RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
        ).into(holder.img)
    }

    override fun getItemCount(): Int {
        return brands.size
    }
}