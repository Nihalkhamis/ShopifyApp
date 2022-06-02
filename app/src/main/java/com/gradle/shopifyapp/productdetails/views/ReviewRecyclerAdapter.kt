package com.gradle.shopifyapp.productdetails.views

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.me.view.OrderModel
import com.gradle.shopifyapp.model.ReviewModel

class ReviewRecyclerAdapter(var reviews: List<ReviewModel>) :
    RecyclerView.Adapter<ReviewRecyclerAdapter.Holder>() {

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var view: View = itemView
        var userName: TextView = view.findViewById(R.id.userName)
        var userReview: TextView = view.findViewById(R.id.user_review)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.review_row, parent, false)
        return ReviewRecyclerAdapter.Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.userName.text = reviews[position].userName
        holder.userReview.text = reviews[position].userReview
    }

    override fun getItemCount(): Int {
        return reviews.count()
    }

}


