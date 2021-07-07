package com.bitage.kapp.editchallenge

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.EditChallengeBinding
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress

/**
 * Implementation of view for edit challenge screen
 */
class EditChallengeViewImpl(private val editMode: Boolean)
    : EditChallengeView {
    private lateinit var binding: EditChallengeBinding

    private lateinit var progressArray: Array<StepProgress>
    private lateinit var stepArray: Array<Int>
    private lateinit var viewModel: EditChallengeViewModel
    private lateinit var screen: Screen

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onDestroy method
     */
    override fun onDestroy() {
        binding.unbind()
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
        binding = DataBindingUtil.setContentView(screen.getActivity(), R.layout.edit_challenge)
    }

    /**
     * get the real android view
     */
    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: EditChallengeViewModel) {
        this.viewModel = viewModel
        binding.viewmodel = viewModel
        initDataBinder()
    }

    override fun customizeActionBar(actionBar: ActionBar?) {}

    private fun initDataBinder() {
        val title = if (editMode) R.string.edit_challenge_title else R.string.new_challenge_title
        screen.getActivity().setTitle(title)
        with(binding) {
            setLifecycleOwner(screen)

            challengeName.isEnabled = editMode.not()
            progressArray = StepProgress.values()
            stepArray = androidView().resources.getIntArray(R.array.challenge_step).toTypedArray()
            progress = progressArray
            steps = stepArray
            challenges = ChallengeType.values()

            challengeName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val item = ChallengeType.values()[position]
                    viewModel.loadChallengeProgress(item)
                }
            }

            confirmChanges.setOnClickListener {
                viewModel.challengeUpdate.observe(screen, Observer {
                    if (it == true) {
                        Toast.makeText(screen.getActivity(), "Challenge changed", Toast.LENGTH_SHORT).show()
                        screen.finish()
                    }
                })
                val challengeType = ChallengeType.values()[challengeName.selectedItemPosition]
                val step = stepArray[challengeStep.selectedItemPosition]
                val progress = StepProgress.values()[challengeStepProgress.selectedItemPosition]
                val goal = challengeGoal.text.toString().toInt()
                val series = challengeSeries.text.toString().toInt()
                viewModel.applyChanges(challengeType, step, progress, goal, series)
            }
            if (!editMode) {
                viewModel.challengeProgress.observe(screen, Observer { ch ->
                    ch?.let {
                        challengeGoal.setText(it.goal.toString())
                        challengeSeries.setText(it.series.toString())
                        challengeStep.setSelection(it.step - 1)
                        challengeStepProgress.setSelection(it.progress.ordinal)
                    }
                })
            }
        }
    }
}