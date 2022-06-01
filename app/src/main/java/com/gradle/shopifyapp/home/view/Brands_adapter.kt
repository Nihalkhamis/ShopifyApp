package com.gradle.shopifyapp.home.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.SmartCollection

class Brands_adapter(var context: Context,var onBrandClickListener : OnBrandClickListener): RecyclerView.Adapter<Brands_adapter.ViewHolder>() {

   // var brands = intArrayOf(R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4)

    private var brands = emptyList<SmartCollection>()

    fun setBrands(brands: List<SmartCollection>) {
        this.brands = brands
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.brand_img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.brands_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(brands[position].image.src).apply(
            RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
        ).into(holder.img)
        holder.img.setOnClickListener {
            onBrandClickListener.onClick((brands[position]))
        }
        Log.i("TAG","BRAND IMAGE: "+brands[position].image.src)
    }

    override fun getItemCount(): Int {
        return brands.size
    }
}