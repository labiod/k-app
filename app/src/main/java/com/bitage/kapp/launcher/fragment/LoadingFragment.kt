package com.bitage.kapp.launcher.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bitage.kapp.KFragment
import com.bitage.kapp.R
import com.bitage.kapp.databinding.LoadingScreenBinding
import com.bitage.kapp.launcher.LauncherViewModel

class LoadingFragment : KFragment<LoadingScreenBinding, LauncherViewModel>() {

    override fun getFragmentResId(): Int = R.layout.loading_screen

    override fun initBinding() {
    }

    override fun deinitBinder() {}
}