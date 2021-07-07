package com.bitage.kapp.launcher

import android.content.ContentValues
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.LauncherViewBinding
import com.bitage.kapp.home.HomeActivity
import com.bitage.kapp.launcher.fragment.LoadingFragment
import com.bitage.kapp.launcher.fragment.WizardFragment
import com.bitage.kapp.model.UserInfo
import com.bitage.kapp.model.UserInfoType
import com.bitage.kapp.presentation.KView

class LauncherView : KView<LauncherViewModel> {
    interface Listener {
        fun onWizardNext(fields: UserInfo)
    }
    private var screen : Screen? = null
    private lateinit var binding: LauncherViewBinding
    var listener : Listener? = null

    override fun onCreate() {
        // Create view
    }

    override fun onDestroy() {
        // onDestroy
        screen = null
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
        initView()
    }

    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: LauncherViewModel) {
    }

    override fun customizeActionBar(actionBar: ActionBar?) {

    }

    fun loadWizardView() {
        screen?.let {
            val fragmentManager = it.getSupportFragmentManager()
            fragmentManager
                .beginTransaction()
                .replace(R.id.launcher_container, WizardFragment())
                .runOnCommit {
                    val nextButton = binding.launcherContainer.findViewById<Button>(R.id.wizardNext)
                    nextButton.setOnClickListener {
                        val userName = binding.launcherContainer.findViewById<EditText>(R.id.username_field).text
                        val fields = UserInfo.EMPTY
                        fields.put(UserInfoType.USERNAME, userName.toString())
                        listener?.onWizardNext(fields)
                    }
                }
                .commit()
        }
    }

    fun loadMainScreen() {
        screen?.let {
            val intent = Intent(it.getActivity(), HomeActivity::class.java)
            it.startActivity(intent)
        }
    }

    private fun initView() {
        screen?.let {
            if (!::binding.isInitialized) {
                binding = DataBindingUtil.setContentView(it.getActivity(), R.layout.launcher_view)
                val fragmentManager = it.getSupportFragmentManager()
                fragmentManager
                    .beginTransaction()
                    .add(R.id.launcher_container, LoadingFragment())
                    .commit()
            }
        }
    }
}