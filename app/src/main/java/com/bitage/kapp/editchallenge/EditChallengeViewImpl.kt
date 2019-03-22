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
        binding.setLifecycleOwner(screen)

        binding.editMode = editMode
        binding.challengeName.isEnabled = editMode.not()
        progressArray = StepProgress.values()
        val challenges = ChallengeType.values()
        stepArray = androidView().resources.getIntArray(R.array.challenge_step).toTypedArray()
        binding.progress = progressArray
        binding.steps = stepArray
        binding.challenges = challenges

        binding.challengeName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = ChallengeType.values()[position]
                viewModel.loadChallengeProgress(item)
            }
        }

        binding.confirmChanges.setOnClickListener {
            viewModel.challengeUpdate.observe(screen, Observer {
                if (it == true) {
                    Toast.makeText(screen.getActivity(), "Challenge changed", Toast.LENGTH_SHORT).show()
                    screen.finish()
                }
            })
            val challengeType = ChallengeType.values()[binding.challengeName.selectedItemPosition]
            val step = stepArray[binding.challengeStep.selectedItemPosition]
            val progress = StepProgress.values()[binding.challengeStepProgress.selectedItemPosition]
            val goal = binding.challengeGoal.text.toString().toInt()
            val series = binding.challengeSeries.text.toString().toInt()
            viewModel.applyChanges(challengeType, step, progress, goal, series)
        }
        if (!editMode) {
            viewModel.challengeProgress.observe(screen, Observer { ch ->
                ch?.let {
                    binding.challengeGoal.setText(it.goal.toString())
                    binding.challengeSeries.setText(it.series.toString())
                    binding.challengeStep.setSelection(it.step - 1)
                    binding.challengeStepProgress.setSelection(it.progress.ordinal)
                }
            })
        }
    }
}