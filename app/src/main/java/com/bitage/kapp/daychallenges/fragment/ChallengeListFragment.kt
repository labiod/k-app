package com.bitage.kapp.daychallenges.fragment

import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitage.datasource.DialogButtonDataSource
import com.bitage.kapp.KFragment
import com.bitage.kapp.R
import com.bitage.kapp.databinding.ChallengesListFragmentBinding
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.OnChallengeActionListener
import com.bitage.kapp.daychallenges.view.KappChallengeListView
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.Template
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.ui.adapter.TemplatesDialogAdapter
import com.bitage.kapp.daychallenges.adapter.TodayChallengesAdapter
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.naw_views.KDialog

class ChallengeListFragment : KFragment<ChallengesListFragmentBinding, DayChallengeViewModel>(), OnChallengeActionListener {

    private var listener: KappChallengeListView.Listener? = null
    private var challengesList: RecyclerView? = null
    private var adapter: TodayChallengesAdapter? = null
    private var templateAdapter: TemplatesDialogAdapter? = null

    private val templateObserver = Observer<List<Template>> {
        templateAdapter?.notifyDataSetChanged()
    }

    private val challengesObserver = Observer<List<Challenge>> {
        Log.d("KGB", "test")
        adapter?.notifyDataSetChanged()
    }

    fun setListener(listener: KappChallengeListView.Listener) {
        this.listener = listener
    }

    fun loadTemplateData() {
        val onChooseListener = DialogInterface.OnClickListener { dialog, which ->
            Log.d(Constants.GLOBAL_TAG, "on choose $which")
            templateAdapter?.getItem(which)?.let {
                viewModel.loadDataFromTemplate(it)
            }
        }
        activity?.let { act ->
            val dialog = AlertDialog.Builder(act)
                .setTitle(act.getString(R.string.template_list_dialog_title))
                .setAdapter(templateAdapter, onChooseListener)
                .create()
            dialog.show()
        }
    }

    override fun onChallengeFinish(challenge: Challenge) {
        viewModel.updateChallenge(challenge)
        viewModel.updateProgress(challenge)
    }

    override fun onChallengeDelete(challenge: Challenge) {
        context?.let {
            KDialog.create(
                it,
                R.string.challenge_list_delete_msg,
                DialogButtonDataSource(R.string.dialog_button_cancel) {dialog, buttonId ->
                    dialog.cancel()
                },
                DialogButtonDataSource(R.string.dialog_button_confirm) {dialog, buttonId ->
                    viewModel.deleteChallenge(challenge)
                    dialog.dismiss()
                }
            ).show()
        }

    }

    override fun onChallengeEdit(challenge: Challenge) {
        listener?.onEditChallenge(challenge)
    }

    override fun getFragmentResId(): Int = R.layout.challenges_list_fragment

    override fun initBinding() {
        initRecyclerView()
        binding.todayDate = viewModel.getDate(Constants.APP_DATE_FORMAT)
        binding.fab.setOnClickListener {
            listener?.onAddChallengeButtonClicked()
        }
        initViewModel()
    }

    override fun deinitBinder() {
        viewModel.templates.removeObserver(templateObserver)
        viewModel.challenges.removeObserver(challengesObserver)
    }

    private fun initRecyclerView() {
        challengesList = binding.challengesList
        challengesList?.let {
            it.setHasFixedSize(true)
            val layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            it.layoutManager = layoutManager
            it.addItemDecoration(androidx.recyclerview.widget.DividerItemDecoration(activity, layoutManager.orientation))
        }
    }

    private fun initViewModel() {
        activity?.let {
            adapter = TodayChallengesAdapter(viewModel)
            templateAdapter = TemplatesDialogAdapter(viewModel.templates)
            viewModel.templates.observe(it, templateObserver)
            challengesList?.adapter = adapter
            viewModel.challenges.observe(it, challengesObserver)
            adapter?.setOnChallengeActionListener(this)
        }
    }
}
