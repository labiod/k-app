package com.bitage.kapp.rules

import android.app.Activity
import androidx.test.rule.ActivityTestRule
import com.bitage.kapp.KApplication
import com.bitage.kapp.KApplicationTest
import com.bitage.kapp.di.module.RepositoryModule

/**
 * Test rule class for testing app with dagger
 */
class DaggerActivityTestRule<T : Activity>(activityClass: Class<T>?) :
    ActivityTestRule<T>(activityClass, false, false) {

    /**
     * Module that can be replace in test
     */
    var repoModule = RepositoryModule(KApplicationTest.instance)

    override fun beforeActivityLaunched() {
        val application: KApplicationTest = KApplicationTest.instance
        application.repositoryModule = repoModule
    }
}