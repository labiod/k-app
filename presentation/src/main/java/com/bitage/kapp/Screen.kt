package com.bitage.kapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

interface Screen: LifecycleOwner {

    fun getActivity(): Activity

    fun startActivity(intent: Intent)

    fun finish()

    fun runOnUi(action: () -> Unit)

    fun getSupportFragmentManager(): FragmentManager

    fun onBackPressed()
}