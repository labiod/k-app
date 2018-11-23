package com.bitage.kapp.home

/**
 * Implementation of presenter for home screen
 */
class HomePresenterImpl(
    private val viewModel: HomeViewModel,
    private val view: HomeView
) : HomePresenter {
    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
        view.onCreate()
        view.initModel(viewModel)
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }
}