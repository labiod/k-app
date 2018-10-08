package com.kgb.kapp

import android.app.Application

class KApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

    companion object {
        private lateinit var _instance : KApplication
        val instance: KApplication
            get() = _instance


    }
}