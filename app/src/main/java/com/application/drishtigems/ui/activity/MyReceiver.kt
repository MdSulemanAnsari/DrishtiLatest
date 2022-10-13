package com.application.drishtigems.ui.activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class MyReceiver(var listener: ConnectivityReceiverListener?):BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
     if (isConnectedOrConnecting()){
         listener?.onNetworkConnectionEnabled()
     }
     else{
         listener?.onNetworkConnectionDisabled()
     }
    }
     fun isConnectedOrConnecting(): Boolean {
        val connMgr = DrishtiGemsApp.instance?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
    interface ConnectivityReceiverListener {
        fun onNetworkConnectionEnabled()
        fun onNetworkConnectionDisabled()
    }
}
