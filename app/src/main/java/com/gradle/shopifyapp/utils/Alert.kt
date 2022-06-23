package com.gradle.shopifyapp.utils

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import androidx.core.content.ContextCompat.startActivity
import com.gradle.shopifyapp.databinding.NoInternetScreenBinding
import android.provider.Settings

class Alert {
    companion object{
        lateinit var dialog:AlertDialog

        fun makeAlert(context:Context):AlertDialog{
            var binding = NoInternetScreenBinding.inflate(LayoutInflater.from(context))
            val builder = AlertDialog.Builder(context)
            builder.setView(binding.root)
            dialog = builder.create()
            dialog.setCancelable(false)
           // dialog.show()
            return dialog
        }
//        fun dismissAlert(){
//            dialog.dismiss()
//        }
    }


}