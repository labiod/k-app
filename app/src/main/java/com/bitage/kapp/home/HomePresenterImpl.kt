package com.bitage.kapp.home

import io.reactivex.functions.Consumer

/**
 * Implementation of presenter for home screen
 */
class HomePresenterImpl(
    private val viewModel: HomeViewModel
) : HomePresenter {
    private lateinit var view: HomeView
    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onCreate method
     */
    override fun onCreate() {
    }

    override fun onResume() {
        view.onResume()
    }

    override fun onPause() {}

    override fun attachView(view: HomeView) {
        this.view = view
        view.attachViewModel(viewModel)
        viewModel.getUserInfo(Consumer { userInfo -> view.setUserInfo(userInfo) })
    }

    /**
     * Control presenter lifecycle. It should be called in Activity or fragment in onDestroy method
     */
    override fun onDestroy() {
        view.onDestroy()
    }
}