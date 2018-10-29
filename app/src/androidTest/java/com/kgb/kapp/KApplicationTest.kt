package com.kgb.kapp

import com.kgb.kapp.di.component.ApplicationComponent

class KApplicationTest : KApplication() {
    var testComponent: ApplicationComponent? = null

    override fun getComponent(): ApplicationComponent {
        return testComponent ?: super.getComponent()
    }
}