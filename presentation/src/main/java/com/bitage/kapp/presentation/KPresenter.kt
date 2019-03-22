package com.bitage.kapp.presentation

/**
 * Base presenter for all presenter class
 */
interface KPresenter<T : KView<*>> {

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    fun onCreate()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onResume method
     */
    fun onResume()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onPause method
     */
    fun onPause()

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    fun onDestroy()

    /**
     * Attach view to presenter
     */
    fun attachView(view: T)
}