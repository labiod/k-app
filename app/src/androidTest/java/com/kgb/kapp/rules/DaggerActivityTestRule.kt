package com.kgb.kapp.rules

import android.app.Activity
import android.support.test.rule.ActivityTestRule
import com.kgb.kapp.KApplication
import com.kgb.kapp.di.component.DaggerApplicationComponent
import com.kgb.kapp.di.module.RepositoryModule

class DaggerActivityTestRule<T : Activity>(activityClass: Class<T>?) :
    ActivityTestRule<T>(activityClass, false, false) {

    var repoModule = RepositoryModule(KApplication.instance)

    override fun beforeActivityLaunched() {
        val application : KApplication = KApplication.instance
        val builder = DaggerApplicationComponent.builder()
            .application(application)
            .plus(repoModule)
            .build()
        application.appComponent = builder
    }


}