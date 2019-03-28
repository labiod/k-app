package com.bitage.kapp.launcher

import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.presentation.KPresenter
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

class LauncherPresenter(private val viewModel: LauncherViewModel) : KPresenter<LauncherView>, LauncherView.Listener {

    private var view: LauncherView? = null
    override fun onCreate() {
        // Create presenter
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun onDestroy() {
        view?.onDestroy()
        this.view = null
    }

    override fun attachView(view: LauncherView) {
        this.view = view
        view.listener = this
        initViewModel()
    }

    override fun onWizardNext(fields: UserInfo) {
        viewModel.setupUser(fields, Action {
            checkUserSetup()
        })
    }

    private fun initViewModel() {
        checkUserSetup()
    }

    private fun checkUserSetup() {
        viewModel.checkUserSetup(Consumer { result ->
            if (result) {
                view?.loadMainScreen()
            } else {
                view?.loadWizardView()
            }
        })
    }
}