package com.gradle.shopifyapp.productdetails.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.me.view.OrderModel

class SizeRecyclerAdapter(var context:Context,var sizes: List<String>, var clickInterface: OnclickInterface) :
    RecyclerView.Adapter<SizeRecyclerAdapter.Holder>() {
    private var checkedPosition = -1

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: View = itemView
        var sizeText: TextView = view.findViewById(R.id.size_text)
        var sizeCard: CardView = view.findViewById(R.id.size_Card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.size_row, parent, false)
        return SizeRecyclerAdapter.Holder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        holder.sizeText.text = sizes[position]
        if(position==checkedPosition){

            holder.sizeCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            holder.sizeText.setTextColor(
                ContextCompat.getColor(context, R.color.white)
            )

        }else{

            holder.sizeCard.setCardBackgroundColor(ContextCompat.getColor(context, androidx.browser.R.color.browser_actions_bg_grey))
            holder.sizeText.setTextColor(
                ContextCompat.getColor(context, R.color.dark_color)
            )

        }

        holder.sizeCard.setOnClickListener {
            clickInterface.onClick(sizes[position])
            checkedPosition = position

        }
    }

    override fun getItemCount(): Int {
        return sizes.count()
    }
}


