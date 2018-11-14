package com.bitage.kapp.presentation

/**
 * Base presenter for all presenter class
 */
interface KPresenter {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    fun onCreate()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    fun onDestroy()
}