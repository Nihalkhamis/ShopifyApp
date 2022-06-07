package com.gradle.shopifyapp.shoppingCart.View

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.draft_model.DraftOrder
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.draft_model.Draft_orders_list
import com.gradle.shopifyapp.draft_model.NoteAttribute
import com.gradle.shopifyapp.model.SmartCollection

class ShoppingCartAdapter(var context: Context): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    private var shoppingCartItems = emptyList<DraftOrder>()

    fun setShoppingCart(shoppingCartItems:List<DraftOrder>) {
        this.shoppingCartItems = shoppingCartItems
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.shoppingCartImg)
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val price: TextView = itemView.findViewById(R.id.itemPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context).load(shoppingCartItems[position].note_attributes!![0].value).apply(
            RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
        ).into(holder.img)
        holder.title.text = shoppingCartItems[position].line_items!![0].title
        holder.price.text = shoppingCartItems[position].line_items!![0].price + " " + shoppingCartItems[position].currency
    }

    override fun getItemCount(): Int {
        return shoppingCartItems.size
    }
}