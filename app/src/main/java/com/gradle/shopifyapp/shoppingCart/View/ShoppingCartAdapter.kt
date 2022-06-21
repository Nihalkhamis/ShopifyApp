package com.gradle.shopifyapp.shoppingCart.View

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class ShoppingCartAdapter(var context: Context,var cartOnClickListener: CartOnClickListener): RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {
    lateinit var preference: MyPreference

    private var shoppingCartItems = ArrayList<Draft_order>()
    var count = 0

    fun setShoppingCart(shoppingCartItems:ArrayList<Draft_order>) {
        Log.i("TAG", "setShoppingCart: ${shoppingCartItems.size}")
        this.shoppingCartItems = shoppingCartItems
        notifyDataSetChanged()
    }

    fun getProductId(position: Int): Draft_order? {
        return shoppingCartItems.get(position)
    }


    inner class ViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.shoppingCartImg)
        val title: TextView = itemView.findViewById(R.id.itemTitle)
        val price: TextView = itemView.findViewById(R.id.itemPrice)
        val quantity: TextView = itemView.findViewById(R.id.count_editText)
        val add: Button = itemView.findViewById(R.id.add_btn)
        val remove: Button = itemView.findViewById(R.id.remove_btn)
//        val favorite: ImageView = itemView.findViewById(R.id.favorite_img)
        val item: CardView = itemView.findViewById(R.id.shopping_cart_item)
        val size: TextView = itemView.findViewById(R.id.size_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.shopping_cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        preference = MyPreference.getInstance(context)!!
        Glide.with(context).load(shoppingCartItems[position].draft_order!!.note_attributes!![0].value).apply(
            RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
        ).into(holder.img)
        holder.title.text = shoppingCartItems[position].draft_order?.line_items!![0].title
        holder.price.text = (String.format("%.2f",shoppingCartItems[position].draft_order?.line_items!![0].price!!.toDouble() * (preference.getData(Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0))) + (preference.getData(Constants.TOCURRENCY))
        holder.quantity.text = shoppingCartItems[position].draft_order!!.line_items!![0].quantity.toString()
        holder.size.text = shoppingCartItems[position].draft_order!!.line_items!![0].variant_title!!.substringBefore("/")
        holder.add.setOnClickListener{
            count = shoppingCartItems[position].draft_order!!.line_items!![0].quantity!!
            holder.quantity.text = (count!!+1).toString()
            cartOnClickListener.onAddProduct(shoppingCartItems[position])
        }
        holder.remove.setOnClickListener{
            count = shoppingCartItems[position].draft_order!!.line_items!![0].quantity!!
            count = count-1
            if(count<1){
             //   cartOnClickListener.onRemoveProduct(shoppingCartItems[position])
                Log.i("POSITIONNNN",position.toString())
             //   shoppingCartItems.removeAt(position)
             //   notifyDataSetChanged()

            }else{
                holder.quantity.text = (count).toString()
                cartOnClickListener.onRemoveProduct(shoppingCartItems[position])
            }
        }

        holder.item.setOnClickListener {
            cartOnClickListener.onClickProduct(shoppingCartItems[position])
        }
    }

    override fun getItemCount(): Int {
        return shoppingCartItems.size
    }
}