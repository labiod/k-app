package com.bitage.kapp

import com.bitage.kapp.presentation.KFragmentPresenter
import com.bitage.kapp.presentation.KFragmentView
import com.bitage.kapp.presentation.KView

abstract class KFragmentActivity<BaseView : KFragmentView<*, SubView>, SubView : KView<*>> : KActivity<BaseView>() {
    abstract override val presenter: KFragmentPresenter<BaseView, SubView>
    abstract override val view: BaseView

    override fun onAttachFragment(fragment: androidx.fragment.app.Fragment?) {
        super.onAttachFragment(fragment)
        presenter.attachFragmentView(fragment as SubView)
    }
}