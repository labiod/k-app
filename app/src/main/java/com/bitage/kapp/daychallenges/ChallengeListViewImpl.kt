package com.bitage.kapp.daychallenges

import android.arch.lifecycle.Observer
import android.content.DialogInterface
import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.R
import com.bitage.kapp.ui.adapter.TemplatesDialogAdapter
import com.bitage.kapp.ui.adapter.TodayChallengesAdapter
import com.bitage.kapp.databinding.DayChallengesBinding
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.template.TemplateActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * List of challenges view implementation class
 */
class ChallengeListViewImpl(private val activity: TodayChallengesActivity) : ChallengeListView {
    private lateinit var binding: DayChallengesBinding
    private lateinit var viewModel: DayChallengeViewModel

    private var adapter: TodayChallengesAdapter? = null
    private var templateAdapter: TemplatesDialogAdapter? = null

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
        binding = DataBindingUtil.setContentView(activity, R.layout.day_challenges)
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    /**
     * Init view with viewModel
     * @param viewModel - view model for this view
     */
    override fun initView(viewModel: DayChallengeViewModel) {
        this.viewModel = viewModel
        initBinder()
    }

    /**
     * Show 'Create new template' view
     */
    override fun showCreateTemplateView() {
        val intent = Intent(activity, TemplateActivity::class.java)
        activity.startActivity(intent)
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
        val dialog = AlertDialog.Builder(activity)
            .setTitle("Templates list")
            .setAdapter(templateAdapter, onChooseListener)
            .create()
        dialog.show()
    }

    private fun initBinder() {
        val dateFormat = SimpleDateFormat("EEE MMM d, ''yy", Locale.getDefault())
        binding.todayDate = viewModel.getDate(dateFormat)
        initRecyclerView()
        initViewModel()
        binding.fab.setOnClickListener {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = viewModel.getTime()
            val intent = Intent(it.context, EditChallengeActivity::class.java)
            intent.putExtra(Constants.CURRENT_DATE_DAY, calendar.get(Calendar.DAY_OF_MONTH))
            intent.putExtra(Constants.CURRENT_DATE_MONTH, calendar.get(Calendar.MONTH))
            intent.putExtra(Constants.CURRENT_DATE_YEAR, calendar.get(Calendar.YEAR))
            activity.startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        binding.challengesList.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.challengesList.layoutManager = layoutManager
        binding.challengesList.addItemDecoration(DividerItemDecoration(activity, layoutManager.orientation))
    }

    private fun initViewModel() {
        adapter = TodayChallengesAdapter(viewModel)
        templateAdapter = TemplatesDialogAdapter(viewModel.templates)
        viewModel.templates.observe(activity, Observer {
            templateAdapter?.notifyDataSetChanged()
        })
        binding.challengesList.adapter = adapter
        viewModel.challenges.observe(activity, android.arch.lifecycle.Observer {
            adapter?.notifyDataSetChanged()
        })
        viewModel.templates.observe(activity, android.arch.lifecycle.Observer {
            templateAdapter?.notifyDataSetChanged()
        })
    }
}