package com.gradle.shopifyapp.me.view

import android.content.Context
import android.icu.text.Transliterator
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
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference

class ProductRecyclerViewAdapter(var favProducts : List<Draft_order>,var context: Context,var onWishListItemClick: OnWishListItemClick) : RecyclerView.Adapter<ProductRecyclerViewAdapter.Holder>() {
    lateinit var preference: MyPreference

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var view: View =itemView
        var productImg:ImageView = view.findViewById(R.id.product_img)
       // var favoriteImage: ImageView = view.findViewById(R.id.favorite_img)
        var priceText : TextView = view.findViewById(R.id.price_Text_wish_list)
        var currencyType_txt : TextView = view.findViewById(R.id.currencyType_txt)
        var descriptionText : TextView = view.findViewById(R.id.product_description_wish_list)
        val productCard: CardView = itemView.findViewById(R.id.productCard)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        return ProductRecyclerViewAdapter.Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        preference = MyPreference.getInstance(context)!!

        holder.priceText.text  = (favProducts[position].draft_order?.line_items!![0].price!!.toDouble() * (preference.getData(
            Constants.CURRENCYRESULT)
            ?.toDouble() ?: 1.0)).toString()
        holder.descriptionText.text =favProducts[position].draft_order?.line_items!![0].title




        holder.currencyType_txt.text = preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")

        Glide.with(context).load(favProducts[position].draft_order!!.note_attributes!![0].value).into(holder.productImg)

        holder.productCard.setOnClickListener {
            onWishListItemClick.onClick(favProducts[position])
        }
       // holder.productImg.setImageResource(R.drawable.cartoon)

    }

    override fun getItemCount(): Int {
      return favProducts.count()
    }


}