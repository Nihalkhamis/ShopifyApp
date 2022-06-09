package com.gradle.shopifyapp.settings.currency.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Currency
import java.util.ArrayList

class CurrencyAdapter(
    val context: Context,
    private var currencies: ArrayList<Currency>,
    var onCurrencyItemClickListener: OnCurrencyItemClickListener
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyHolder>() {


    var selected: Int = -1


    fun setCurrencies(currenciesList: List<Currency>) {
        this.currencies.apply {
            clear()
            add(Currency("EGP",true,"2022-06-08T14:12:29+02:00"))
            addAll(currenciesList)
            notifyDataSetChanged()
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.currency_item, parent, false)
        return CurrencyAdapter.CurrencyHolder(view)
    }

    override fun onBindViewHolder(holder: CurrencyHolder, position: Int) {

        if (selected == position) {
            holder.currency_txt.setBackgroundResource(R.color.orange)
        } else {
            holder.currency_txt.setBackgroundResource(R.color.off_white)
        }

        holder.currency_txt.text = currencies[position].currency
        holder.currency_txt.setOnClickListener {

            if (selected == position){
               return@setOnClickListener
            } else{
                selected = position
                onCurrencyItemClickListener.onClick(currencies[position])
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return currencies.count()
    }

    class CurrencyHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val currency_txt: TextView = itemView.findViewById(R.id.currency_txt)

    }
}