package com.bitage.kapp.daychallenges

import androidx.lifecycle.Observer
import android.content.DialogInterface
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.ui.adapter.TemplatesDialogAdapter
import com.bitage.kapp.ui.adapter.TodayChallengesAdapter
import com.bitage.kapp.databinding.DayChallengesBinding
import com.bitage.dsl.get
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import java.util.Calendar

/**
 * List of challenges view implementation class
 */
class ChallengeListViewImpl : ChallengeListView {
    private lateinit var binding: DayChallengesBinding
    private lateinit var viewModel: DayChallengeViewModel
    private lateinit var screenHandler: Screen

    private var adapter: TodayChallengesAdapter? = null
    private var templateAdapter: TemplatesDialogAdapter? = null

    private val templateObserver = Observer<List<Template>> {
        templateAdapter?.notifyDataSetChanged()
    }

    private val challengesObserver = Observer<List<Challenge>> {
        adapter?.notifyDataSetChanged()
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    override fun onAttached(screen: Screen) {
        binding = DataBindingUtil.setContentView(screen.getActivity(), R.layout.day_challenges)
        this.screenHandler = screen
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    override fun onResume() {
        initBinder()
    }

    override fun onPause() {
        deinitBinder()
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: DayChallengeViewModel) {
        this.viewModel = viewModel
    }

    /**
     * Load list of template
     */
    override fun loadTemplateData() {
        val onChooseListener = DialogInterface.OnClickListener { dialog, which ->
            Log.d(Constants.GLOBAL_TAG, "on choose $which")
            templateAdapter?.getItem(which)?.let {
                viewModel.loadDataFromTemplate(it)
            }
        }
        val activity = screenHandler.getActivity()
        val dialog = AlertDialog.Builder(activity)
            .setTitle(activity.getString(R.string.template_list_dialog_title))
            .setAdapter(templateAdapter, onChooseListener)
            .create()
        dialog.show()
    }

    override fun setChallengeActionListener(listener: OnChallengeActionListener) {
        adapter?.setOnChallengeActionListener(listener)
    }

    override fun customizeActionBar(actionBar: ActionBar?) {
        actionBar?.let {
            it.setDisplayShowCustomEnabled(true)
            it.setCustomView(R.layout.challenges_action_bar)
            val actionBarView = it.customView
            val title: TextView = actionBarView.findViewById(R.id.action_bar_title)
            val subTitle: TextView = actionBarView.findViewById(R.id.action_bar_subtitle)
            subTitle.text = viewModel.getDate(Constants.APP_DATE_FORMAT)
            title.setText(R.string.your_challenges_title)
        }
    }

    private fun initBinder() {
        binding.todayDate = viewModel.getDate(Constants.APP_DATE_FORMAT)
        initRecyclerView()
        initViewModel()
        binding.fab.setOnClickListener {
            val intent = Intent(it.context, EditChallengeActivity::class.java)
            intent.putExtra(Constants.CURRENT_DATE_DAY, viewModel.getTime() get Calendar.DAY_OF_MONTH)
            intent.putExtra(Constants.CURRENT_DATE_MONTH, viewModel.getTime() get Calendar.MONTH)
            intent.putExtra(Constants.CURRENT_DATE_YEAR, viewModel.getTime() get Calendar.YEAR)
            screenHandler.startActivity(intent)
        }
    }

    private fun deinitBinder() {
        viewModel.templates.removeObserver(templateObserver)
        viewModel.challenges.removeObserver(challengesObserver)
    }

    private fun initRecyclerView() {
        binding.challengesList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(screenHandler.getActivity(), RecyclerView.VERTICAL, false)
        binding.challengesList.layoutManager = layoutManager
        binding.challengesList.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(screenHandler.getActivity(), layoutManager.orientation))
    }

    private fun initViewModel() {
        adapter = TodayChallengesAdapter(viewModel)
        templateAdapter = TemplatesDialogAdapter(viewModel.templates)
        viewModel.templates.observe(screenHandler, templateObserver)
        binding.challengesList.adapter = adapter
        viewModel.challenges.observe(screenHandler, challengesObserver)
    }
}