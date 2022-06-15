package com.gradle.shopifyapp.orders.order_details.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class ProductInOrderAdapter(var myProducts:List<Product>, var context:Context,var productOnclickInterface:ProductOnclickListener): RecyclerView.Adapter<ProductInOrderAdapter.Holder>()
{
    lateinit var preference: MyPreference
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view: View =itemView

        var productPriceText : TextView = view.findViewById(R.id.product_price)
        var productNameText : TextView = view.findViewById(R.id.product_name)
        var productTypeText : TextView = view.findViewById(R.id.product_type)
        var productimage : ImageView = view.findViewById(R.id.product_image)

        var orderListItem: CardView = view.findViewById(R.id.product_in_order)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_in_order_row,parent,false)
        return ProductInOrderAdapter.Holder(view)    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        preference = MyPreference.getInstance(context)!!
        var price = ((myProducts[position].variants?.get(0)?.price?.toDouble() ?: 1.0) * (preference.getData(
            Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0)).toString()
        holder.productPriceText.text="$price ${preference.getData(Constants.TOCURRENCY)}"
        holder.productNameText.text=myProducts[position].title
        holder.productTypeText.text = myProducts[position].product_type
        Glide.with(context).load(myProducts[position].image?.src).into(holder.productimage)
        holder.orderListItem.setOnClickListener {
            productOnclickInterface.orderOnClickListener(myProducts[position],price)
        }

    }

    override fun getItemCount(): Int {
       return myProducts.count()
    }

}



