package com.bitage.kapp

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bitage.kapp.presentation.KPresenter
import com.bitage.kapp.presentation.KView
import com.bitage.kapp.presentation.KViewModel

/**
 * Base activity class for K-App
 */
abstract class KActivity<T : KView<Model>, Model : KViewModel> : AppCompatActivity(), Screen {

    /**
     * Get Presenter associated with extending Activity
     */
    protected abstract val presenter: KPresenter<T, Model>

    /**
     * Get view associated with extending Activity
     */
    protected abstract val view: T

    abstract var viewModel: Model

    /**
     * Method call by android when create activity
     *
     */
    final override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)

        presenter.onCreate()
        view.onCreate()
        view.onAttached(this)
        presenter.attachViewModel(viewModel)
        presenter.attachView(view)
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)
        fragment?.let { presenter.attachFragment(it) }
    }

    /**
     * Method call by android system when activity was destroy
     */
    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }

    override fun getActivity(): Activity {
        return this
    }

    override fun runOnUi(action: () -> Unit) {
        runOnUiThread(action)
    }

    /**
     * Setup dependency injection for current activity
     */
    abstract fun setupDependencyInjection()
}