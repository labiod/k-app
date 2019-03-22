package com.bitage.kapp.user

class KAppUserPresenter(val model: UserViewModel) : UserPresenter {
    override fun onCreate() {

    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
    }

    override fun attachView(view: UserView) {
        view.attachViewModel(model)
    }
}