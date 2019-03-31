package com.bitage.kapp.presentation

interface KFragmentView<Model : KViewModel, SubView : KView<Model>> : KView<Model> {
    fun attachSubView(subView: SubView)
}