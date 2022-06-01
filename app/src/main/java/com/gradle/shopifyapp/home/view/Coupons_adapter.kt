package com.gradle.shopifyapp.home.view

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.DiscountCode

class Coupons_adapter(var context: Context): RecyclerView.Adapter<Coupons_adapter.ViewHolder>() {

    private var coupons = emptyList<DiscountCode>()

    fun setCoupons(coupons: List<DiscountCode>) {
        this.coupons = coupons
        notifyDataSetChanged()
    }


    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        var coupon_title: TextView = itemView.findViewById(R.id.coupon_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.coupons_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coupon_title.text = coupons[position].code
    }

    override fun getItemCount(): Int {
        return coupons.size
    }
}