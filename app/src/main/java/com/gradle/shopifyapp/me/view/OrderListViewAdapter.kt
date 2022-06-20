package com.gradle.shopifyapp.me.view

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.OrderModel
import com.gradle.shopifyapp.orders.orders_list.view.OrderOnClickListener
import com.gradle.shopifyapp.productdetails.views.OnclickInterface
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: Holder, position: Int) {

        preference = MyPreference.getInstance(context)!!
        var price = ((orders[position].total_price?.toDouble() ?: 0.0) * (preference.getData(
            Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0)).toString()
       // var date = orders[position].created_at?.let { convertDate(it) }
//        var date = orders[position].created_at?.split(":",limit = 2)
//        var day = date?.get(0)?.split("-")?.get(2)
//        var resultDay = day?.let { convertToDate(it) }

//        Log.d("TAG", "onBindViewHolder: WHOLE DATE->${orders[position].created_at}")
//        Log.d("TAG", "onBindViewHolder: DATE->$date")
//        Log.d("TAG", "onBindViewHolder: DAY->$day")
//        Log.d("TAG", "onBindViewHolder: RESULT DAY->$resultDay")

        holder.dateText.text = orders[position].created_at
        holder.priceText.text =  "$price ${preference.getData(Constants.TOCURRENCY)}"
        holder.orderListItem.setOnClickListener {
            onclickInterface.orderOnClickListener(position)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun convertToDate(dt : String) : String {
//        val l = LocalDate.parse(dt, DateTimeFormatter.ofPattern("dd"))
//        val unix = l.atStartOfDay(ZoneId.systemDefault()).toInstant().epochSecond

        val date = Date(dt.toLong())
        val format = SimpleDateFormat("d", Locale.ENGLISH)
        return format.format(date)

//        return unix.toString()
    }

    override fun getItemCount(): Int {
        return orders.count()
    }
}




