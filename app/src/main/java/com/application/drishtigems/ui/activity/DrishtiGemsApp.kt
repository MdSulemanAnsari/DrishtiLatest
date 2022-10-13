package com.application.drishtigems.ui.activity

import android.app.Application

class DrishtiGemsApp:Application() {
    companion object{
         var instance: DrishtiGemsApp? = null
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    fun getInstance(): DrishtiGemsApp? {
        return instance
    }

}