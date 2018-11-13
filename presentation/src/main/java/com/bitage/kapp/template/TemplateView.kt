package com.bitage.kapp.template

import com.bitage.kapp.presentation.KView

/**
 * View for template screen
 */
interface TemplateView : KView {
    /**
     * Init view with view model
     * @param model - view model for template screen
     */
    fun initView(model: TemplateViewModel)
}