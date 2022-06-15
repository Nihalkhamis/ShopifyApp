package com.gradle.shopifyapp.productBrand.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Product
import com.gradle.shopifyapp.model.ProductModel
import com.gradle.shopifyapp.model.SubCategoryModel
import com.gradle.shopifyapp.utils.Constants
import com.gradle.shopifyapp.utils.MyPreference
import java.util.ArrayList

class ProductBrandAdapter(
    val context: Context,
    private var productsBrand: ArrayList<Product>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<ProductBrandAdapter.ProdcutBrandHolder>() {

    lateinit var preference: MyPreference

    fun setProductsBrand(productsBrandList: List<Product>) {
        this.productsBrand.apply {
            clear()
            addAll(productsBrandList)
            notifyDataSetChanged()
        }
    }

    fun deleteProductBrand(){
        this.productsBrand.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProdcutBrandHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.product_item, parent, false)
        return ProdcutBrandHolder(view)
    }

    override fun onBindViewHolder(holder: ProdcutBrandHolder, position: Int) {
        preference = MyPreference.getInstance(context)!!
        var price = ((productsBrand[position].variants?.get(0)?.price?.toDouble() ?: 1.0) * (preference.getData(Constants.CURRENCYRESULT)?.toDouble() ?: 1.0)).toString()
         holder.price_Text_wish_list.text = price
        //holder.price_Text_wish_list.text = productsBrand[position].variants[0].price
        holder.product_description_wish_list.text = productsBrand[position].title
        var price = (productsBrand[position].variants!![0].price.toDouble() * (preference.getData(Constants.CURRENCYRESULT)
                    ?.toDouble() ?: 1.0)).toString()
        holder.price_Text_wish_list.text = price

        holder.currencyType_txt.text = preference.getDataWithCustomDefaultValue(Constants.TOCURRENCY,"EGP")
        Glide.with(context).load(productsBrand[position].image!!.src).into(holder.product_img)
        holder.productCard.setOnClickListener {
            onItemClickListener.onClick(productsBrand[position],price)
        }
    }

    override fun getItemCount(): Int {
        return productsBrand.count()
    }


    class ProdcutBrandHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val product_img: ImageView = itemView.findViewById(R.id.product_img)
        val product_description_wish_list: TextView = itemView.findViewById(R.id.product_description_wish_list)
        val price_Text_wish_list : TextView = itemView.findViewById(R.id.price_Text_wish_list)
        val currencyType_txt : TextView = itemView.findViewById(R.id.currencyType_txt)
        val productCard : CardView = itemView.findViewById(R.id.productCard)
    }
}