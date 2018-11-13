package com.bitage.kapp.runner

import android.app.Application
import android.content.Context
import android.support.test.runner.AndroidJUnitRunner
import com.bitage.kapp.KApplicationTest

/**
 * Android instrumentation test runner for K-App
 */
class KApplicationRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, KApplicationTest::class.java.name, context)
    }
}