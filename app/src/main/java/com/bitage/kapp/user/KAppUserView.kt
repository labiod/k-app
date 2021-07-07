package com.bitage.kapp.user

import android.view.View
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.UserViewLayoutBinding

class KAppUserView : UserView {
    private lateinit var binding: UserViewLayoutBinding
    private lateinit var screen: Screen
    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
        binding = DataBindingUtil.setContentView(screen.getActivity(), R.layout.user_view_layout)
    }

    override fun androidView(): View {
        return binding.root
    }

    override fun attachViewModel(viewModel: UserViewModel) {
    }

    override fun customizeActionBar(actionBar: ActionBar?) {
    }
}