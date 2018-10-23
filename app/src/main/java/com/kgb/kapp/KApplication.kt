package com.kgb.kapp

import android.app.Application

/**
 * Application class for K-App
 */
class KApplication : Application() {
    /**
     * Method call by android when create application
     */
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        /**
         * Instance of application
         */
        lateinit var instance: KApplication
    }
}