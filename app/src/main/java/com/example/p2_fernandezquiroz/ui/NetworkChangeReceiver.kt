package com.example.p2_fernandezquiroz.ui

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager

class NetworkChangeReceiver : BroadcastReceiver() {
    var onNetworkChange: (() -> Unit)? = null

    override fun onReceive(context: Context, intent: Intent) {
        if (isConnected(context)) {
            onNetworkChange?.invoke()
        }
    }

    @SuppressLint("ServiceCast")
    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }
}