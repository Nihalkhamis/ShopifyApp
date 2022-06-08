package com.gradle.shopifyapp.productdetails.views

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R

class ColorRecyclerAdapter(var context: Context, var colors: List<String>, var clickInterface: OnclickInterface) :
    RecyclerView.Adapter<ColorRecyclerAdapter.Holder>() {
    private var checkedPosition = -1

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: View = itemView
        var colorText: TextView = view.findViewById(R.id.size_text)
        var colorCard: CardView = view.findViewById(R.id.size_Card)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.size_color_row, parent, false)
        return ColorRecyclerAdapter.Holder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: Holder, @SuppressLint("RecyclerView") position: Int) {
        holder.colorText.text = colors[position]
        if(position==checkedPosition){

            holder.colorCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.orange))
            holder.colorText.setTextColor(
                ContextCompat.getColor(context, R.color.white)
            )

        }else{

            holder.colorCard.setCardBackgroundColor(ContextCompat.getColor(context, androidx.browser.R.color.browser_actions_bg_grey))
            holder.colorText.setTextColor(
                ContextCompat.getColor(context, R.color.dark_color)
            )

        }

        holder.colorCard.setOnClickListener {
            clickInterface.onClickForSelectedColor(colors[position])
            checkedPosition = position

        }
    }

    override fun getItemCount(): Int {
        return colors.count()
    }
}