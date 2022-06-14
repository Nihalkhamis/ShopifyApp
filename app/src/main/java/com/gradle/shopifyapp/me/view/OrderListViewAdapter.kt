package com.gradle.shopifyapp.me.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.OrderModel
import com.gradle.shopifyapp.orders.orders_list.view.OrderOnClickListener
import com.gradle.shopifyapp.productdetails.views.OnclickInterface
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class OrderListViewAdapter(var orders : List<OrderModel>, private val onclickInterface: OrderOnClickListener,var context: Context) : RecyclerView.Adapter<OrderListViewAdapter.Holder>() {
    lateinit var preference: MyPreference
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view: View =itemView
        var priceText :TextView = view.findViewById(R.id.price_Text)
        var dateText :TextView = view.findViewById(R.id.order_date)
        var orderListItem:LinearLayout = view.findViewById(R.id.orderListItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.order_list_item,parent,false)
        return OrderListViewAdapter.Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        preference = MyPreference.getInstance(context)!!
        var price = ((orders[position].total_price?.toDouble() ?: 0.0) * (preference.getData(
            Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0)).toString()
        holder.dateText.text = orders[position].created_at
        holder.priceText.text =  "$price ${preference.getData(Constants.TOCURRENCY)}"
        holder.orderListItem.setOnClickListener {
            onclickInterface.orderOnClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return orders.count()
    }
}




