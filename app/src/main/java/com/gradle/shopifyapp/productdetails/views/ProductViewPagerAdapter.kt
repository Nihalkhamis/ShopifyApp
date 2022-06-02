package com.gradle.shopifyapp.productdetails.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.gradle.shopifyapp.R
import com.gradle.shopifyapp.model.Image
import com.gradle.shopifyapp.model.Product


    class ProductViewPagerAdapter(var context: Context, var images:List<Product.Image>): PagerAdapter() {
     var layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view==`object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var view: View = layoutInflater.inflate(R.layout.slideshow_layout,container,false)
        var img: ImageView = view.findViewById(R.id.imgView)
        Glide.with(context).load(images[position].src).into(img)
        //Glide.with(context).load(images[position]).into(img)
        container.addView(view)
        img.setOnClickListener{
            Toast.makeText(context,"Images"+(position+1), Toast.LENGTH_LONG).show()
        }
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}