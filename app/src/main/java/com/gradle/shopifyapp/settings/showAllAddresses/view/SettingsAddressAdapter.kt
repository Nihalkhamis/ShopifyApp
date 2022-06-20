package com.gradle.shopifyapp.settings.showAllAddresses.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.*
import com.gradle.shopifyapp.settings.addAddress.view.OnAddressItemClickListener
import com.gradle.shopifyapp.utils.MyPreference
import java.util.ArrayList

class SettingsAddressAdapter(
    val context: Context,
    private var addresses: ArrayList<Addresse>,
    private val onAddressItemClickListener: OnAddressItemClickListener
) : RecyclerView.Adapter<SettingsAddressAdapter.SettingsAddressHolder>() {

    lateinit var preference: MyPreference

    fun setAddresses(addressesList: List<Addresse>) {
        this.addresses.apply {
            clear()
            addAll(addressesList)
            notifyDataSetChanged()
        }
    }

    fun deleteAddresses() {
        this.addresses.clear()
        notifyDataSetChanged()
    }

    fun deleteAddressByPosition(position: Int) {
        addresses.removeAt(position)
        notifyItemRemoved(position)
    }

    fun getAddressPosition(position: Int) = addresses.get(position)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsAddressHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.address_item, parent, false)
        return SettingsAddressHolder(view)
    }

    override fun onBindViewHolder(holder: SettingsAddressHolder, position: Int) {
        holder.address_txt.text = addresses[position].address1
        holder.zipCode_txt.text = "zip code: ${addresses[position].zip}"
        holder.phone_txt.text = addresses[position].phone
        holder.city_txt.text = addresses[position].city
        holder.country_txt.text = addresses[position].country
        holder.addressCard.setOnClickListener {

            onAddressItemClickListener.onClick(addresses[position])
        }

    }

    override fun getItemCount(): Int {
        return addresses.count()
    }


    class SettingsAddressHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val address_txt: TextView = itemView.findViewById(R.id.address_txt)
        val zipCode_txt: TextView = itemView.findViewById(R.id.zipCode_txt)
        val phone_txt: TextView = itemView.findViewById(R.id.phone_txt)
        val city_txt: TextView = itemView.findViewById(R.id.city_txt)
        val country_txt: TextView = itemView.findViewById(R.id.country_txt)
        val addressCard: CardView = itemView.findViewById(R.id.addressCard)
    }
}