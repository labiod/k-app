package com.bitage.kapp.user

class KAppUserPresenter : UserPresenter {
    private lateinit var viewModel: UserViewModel

    override fun onCreate() {

    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun attachView(view: UserView) {
        view.attachViewModel(viewModel)
    }

    override fun attachViewModel(viewModel: UserViewModel) {
        this.viewModel = viewModel
    }
}