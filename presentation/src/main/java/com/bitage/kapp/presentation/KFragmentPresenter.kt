package com.bitage.kapp.presentation

interface KFragmentPresenter<BaseView : KFragmentView<*>, SubView : KView<*>> : KPresenter<BaseView> {
    fun attachFragmentView(subView: SubView)
}