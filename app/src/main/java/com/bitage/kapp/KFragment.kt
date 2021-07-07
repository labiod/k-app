package com.bitage.kapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.bitage.kapp.presentation.KViewModel

abstract class KFragment<T : ViewDataBinding, Model : KViewModel> : Fragment() {
    protected lateinit var binding: T

    protected lateinit var viewModel: Model

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val currActivity = activity as KActivity<*, Model>?
        currActivity?.let {
            viewModel = it.viewModel
        }
    }

    override fun onResume() {
        super.onResume()
        initBinding()
    }

    override fun onPause() {
        super.onPause()
        deinitBinder()
    }

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getFragmentResId(), container, false)
        return binding.root
    }
    protected abstract fun deinitBinder()

    protected abstract fun initBinding()

    protected abstract fun getFragmentResId(): Int
}
