package com.gradle.shopifyapp.home.view

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.media.Image
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
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
        var coupon_cv: CardView = itemView.findViewById(R.id.coupon_cardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.coupons_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.coupon_title.text = coupons[position].code
        holder.coupon_cv.setOnClickListener{
            setClipboard(context,coupons[position].code)
        }
    }

    override fun getItemCount(): Int {
        return coupons.size
    }

    private fun setClipboard(context: Context, text: String) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as android.text.ClipboardManager
            clipboard.text = text
            Toast.makeText(context,"Text Copied!", Toast.LENGTH_SHORT).show()
        } else {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("Text Copied!", text)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context,"Text Copied!", Toast.LENGTH_SHORT).show()
        }
    }
}