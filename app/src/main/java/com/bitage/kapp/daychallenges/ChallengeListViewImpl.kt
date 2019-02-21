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
import com.bitage.kapp.editchallenge.EditChallengeActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * List of challenges view implementation class
 */
class ChallengeListViewImpl : ChallengeListView {
    private lateinit var binding: DayChallengesBinding
    private lateinit var viewModel: DayChallengeViewModel
    private lateinit var screenHandler: Screen

    private var adapter: TodayChallengesAdapter? = null
    private var templateAdapter: TemplatesDialogAdapter? = null

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

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: DayChallengeViewModel) {
        this.viewModel = viewModel
        initBinder()
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
            val dateFormat = SimpleDateFormat(Constants.APP_DATE_FORMAT, Locale.getDefault())
            subTitle.text = viewModel.getDate(dateFormat)
            title.setText(R.string.your_challenges_title)
        }
    }

    private fun initBinder() {
        val dateFormat = SimpleDateFormat(Constants.APP_DATE_FORMAT, Locale.getDefault())
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
            screenHandler.startActivity(intent)
        }
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
        viewModel.templates.observe(screenHandler, Observer {
            templateAdapter?.notifyDataSetChanged()
        })
        binding.challengesList.adapter = adapter
        viewModel.challenges.observe(screenHandler, androidx.lifecycle.Observer {
            adapter?.notifyDataSetChanged()
        })
        viewModel.templates.observe(screenHandler, androidx.lifecycle.Observer {
            templateAdapter?.notifyDataSetChanged()
        })
    }
}