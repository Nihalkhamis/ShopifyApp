package com.gradle.shopifyapp.category.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import java.util.ArrayList

class ProductTypeAdapter(
    val context: Context,
    private var productTypes: ArrayList<String>,
    var onProductTypeClickListener: OnProductTypeItemListener
) : RecyclerView.Adapter<ProductTypeAdapter.ProductTypeHolder>() {

    var selected: Int = -1

    fun setProductTypes(productTypesList: List<String>) {
        this.productTypes.apply {
            //weathers = weatherList
            clear()
            addAll(productTypesList)
            notifyDataSetChanged()
        }
    }

    fun deleteProductTypes() {
        this.productTypes.clear()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductTypeHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.product_type_item, parent, false)
        return ProductTypeAdapter.ProductTypeHolder(view)
    }

    override fun onBindViewHolder(holder: ProductTypeHolder, position: Int) {

        holder.productTypeName_txt.text = productTypes[position]


        if (selected == position) {
            holder.cancel_btn.visibility = View.VISIBLE
            holder.productType_layout.setBackgroundResource(R.drawable.product_type_selected_background)
        } else {
            holder.cancel_btn.visibility = View.GONE
            holder.productType_layout.setBackgroundResource(R.drawable.product_type_unselected_background)
        }


        holder.productType_layout.setOnClickListener {
            // check for the next selected item if it's the same as previous so unselect it
            if (selected == position){
                selected =  -1
                onProductTypeClickListener.onClick(productTypes[position],false)

            } else{
                selected = position
                onProductTypeClickListener.onClick(productTypes[position],true)

            }
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return productTypes.count()
    }

    class ProductTypeHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cancel_btn: ImageView = itemView.findViewById(R.id.cancel_btn)
        val productTypeName_txt: TextView = itemView.findViewById(R.id.productTypeName_txt)
        val productType_layout: ConstraintLayout = itemView.findViewById(R.id.productType_layout)
    }
}