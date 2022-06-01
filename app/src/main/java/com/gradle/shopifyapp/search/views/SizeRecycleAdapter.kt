package com.gradle.shopifyapp.search.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Product
import java.util.ArrayList

class SizeRecycleAdapter( val context: Context,var sizes:List<String>) : RecyclerView.Adapter<SizeRecycleAdapter.Holder>() {


    class Holder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sizeText :TextView = itemView.findViewById(R.id.size_text)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.size_row, parent, false)
        return SizeRecycleAdapter.Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.sizeText.text = sizes[position]
    }

    override fun getItemCount(): Int {
        return sizes.count()
    }
}



