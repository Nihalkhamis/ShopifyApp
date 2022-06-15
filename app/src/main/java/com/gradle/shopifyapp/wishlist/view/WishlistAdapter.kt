package com.gradle.shopifyapp.wishlist.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.draft_model.Draft_order
import com.gradle.shopifyapp.shoppingCart.View.CartOnClickListener
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class WishlistAdapter(var context: Context, var onItemClickListener: CartOnClickListener): RecyclerView.Adapter<WishlistAdapter.ViewHolder>() {

    private var favProducts = ArrayList<Draft_order>()
    lateinit var preference: MyPreference


    fun setFavProducts(favProductsList: ArrayList<Draft_order>) {
        Log.i("TAG", "setFavProducts: ${favProductsList.size}")

        this.favProducts.apply {
            clear()
            addAll(favProductsList)
            notifyDataSetChanged()
        }
    }

    fun getProductId(position: Int): Draft_order? {
        return favProducts.get(position)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        preference = MyPreference.getInstance(context)!!

        holder.product_description_wish_list.text = favProducts[position].draft_order?.line_items!![0].title

        holder.price_Text_wish_list.text = (favProducts[position].draft_order?.line_items!![0].price!!.toDouble() * (preference.getData(
            Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0)).toString()

        holder.currencyType_txt.text = preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")

        Glide.with(context).load(favProducts[position].draft_order!!.note_attributes!![0].value).into(holder.product_img)

        holder.productCard.setOnClickListener {
            onItemClickListener.onClickProduct(favProducts[position])
        }

//        Glide.with(context).load(favProducts[position].draft_order!!.note_attributes!![0].value)
//            .apply(
//                RequestOptions().override(200, 200).placeholder(R.drawable.ic_launcher_background)
//            ).into(holder.img)
//        holder.title.text = shoppingCartItems[position].draft_order?.line_items!![0].title
//        holder.price.text =
//            shoppingCartItems[position].draft_order?.line_items!![0].price + " " + (shoppingCartItems[position].draft_order?.currency)
//        holder.quantity.text =
//            shoppingCartItems[position].draft_order!!.line_items!![0].quantity.toString()
//        holder.size.text =
//            shoppingCartItems[position].draft_order!!.line_items!![0].variant_title!!.substringBefore(
//                "/"
//            )
//        holder.add.setOnClickListener {
//            count = shoppingCartItems[position].draft_order!!.line_items!![0].quantity!!
//            holder.quantity.text = (count!! + 1).toString()
//            cartOnClickListener.onAddProduct(shoppingCartItems[position])
//        }
//        holder.remove.setOnClickListener {
//            count = shoppingCartItems[position].draft_order!!.line_items!![0].quantity!!
//            count = count - 1
//            if (count < 1) {
//                cartOnClickListener.onRemoveProduct(shoppingCartItems[position])
//                shoppingCartItems.removeAt(position)
//                notifyDataSetChanged()
//            } else {
//                holder.quantity.text = (count).toString()
//                cartOnClickListener.onRemoveProduct(shoppingCartItems[position])
//            }
//        }
//
//        holder.favorite.setOnClickListener {
//            cartOnClickListener.onDeleteProduct(shoppingCartItems[position].draft_order!!.id.toString())
//        }
    }

    override fun getItemCount(): Int {
        return favProducts.size
    }

    inner class ViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product_img: ImageView = itemView.findViewById(R.id.product_img)
        val product_description_wish_list: TextView =
            itemView.findViewById(R.id.product_description_wish_list)
        val price_Text_wish_list: TextView = itemView.findViewById(R.id.price_Text_wish_list)
        val currencyType_txt: TextView = itemView.findViewById(R.id.currencyType_txt)
        val productCard: CardView = itemView.findViewById(R.id.productCard)
    }
}
