package com.gradle.shopifyapp.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService


class InternetConnection {
    companion object{
        fun isInternetAvailable(context: Context): Boolean {

            var connected = false
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable && networkInfo.isConnected.also {
                    connected = it
                }) {
                Log.i("con", "connected : $connected")
                return connected
            }
            return connected
//            return try {
//                val ipAddr: InetAddress = InetAddress.getByName("google.com")
//                !ipAddr.equals("")
//            } catch (e: Exception) {
//                false
//            }
           // return connected
        }
    }
}