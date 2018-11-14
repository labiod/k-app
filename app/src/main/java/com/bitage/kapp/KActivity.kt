package com.bitage.kapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bitage.kapp.presentation.KPresenter
import com.bitage.kapp.presentation.KView

/**
 * Base activity class for K-App
 */
abstract class KActivity<T : KView> : AppCompatActivity() {

    /**
     * Get Presenter associated with extending Activity
     */
    protected abstract val presenter: KPresenter?

    /**
     * Get view associated with extending Activity
     */
    protected abstract val view: T?

    /**
     * Method call by android when create activity
     */
    final override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)
        presenter?.onCreate()
    }

    /**
     * Method call by android system when activity was destroy
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    /**
     * Setup dependency injection for current activity
     */
    abstract fun setupDependencyInjection()
}