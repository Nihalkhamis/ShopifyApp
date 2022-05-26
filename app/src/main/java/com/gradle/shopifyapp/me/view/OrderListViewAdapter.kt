package com.gradle.shopifyapp.me.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R

class OrderListViewAdapter(var orders : List<OrderModel>) : RecyclerView.Adapter<OrderListViewAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view: View =itemView
        var priceText :TextView = view.findViewById(R.id.price_Text)
        var dateText :TextView = view.findViewById(R.id.order_date)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item,parent,false)
        return OrderListViewAdapter.Holder(view)    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.dateText.text = orders[position].date
        holder.priceText.text =  orders[position].price.toString()
    }

    override fun getItemCount(): Int {
        return orders.count()
    }
}




