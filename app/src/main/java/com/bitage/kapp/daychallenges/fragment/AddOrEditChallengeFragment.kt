package com.bitage.kapp.daychallenges.fragment

import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bitage.kapp.KFragment
import com.bitage.kapp.R
import com.bitage.kapp.databinding.EditChallengeFragmentBinding
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.StepProgress
import com.bitage.kapp.presentation.Constants

class AddOrEditChallengeFragment : KFragment<EditChallengeFragmentBinding, DayChallengeViewModel>() {

    interface Listener {
        fun onChallengeSubmit(success: Boolean)
    }
    private lateinit var stepArray: Array<Int>
    private var editMode = false
    private var listener: Listener? = null
    private val challengeUpdateObserver = Observer<Boolean> {
        if (it == true) {
            Toast.makeText(activity, "Challenge ${if (editMode) "edit" else "add"}", Toast.LENGTH_SHORT).show()
        }
        listener?.onChallengeSubmit(it)
    }
    private val challengeProgressObserver = Observer<Challenge> { ch ->
        ch?.let {
            binding.challengeGoal.setText(it.goal.toString())
            binding.challengeSeries.setText(it.series.toString())
            binding.challengeStep.setSelection(it.step - 1)
            binding.challengeStepProgress.setSelection(it.progress.ordinal)
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    override fun getFragmentResId(): Int = R.layout.edit_challenge_fragment

    override fun initBinding() {
        editMode = arguments?.containsKey(Constants.CHALLENGE_ITEM_ID_KEY) ?: false
        val title = if (editMode) R.string.edit_challenge_title else R.string.new_challenge_title
        stepArray = resources.getIntArray(R.array.challenge_step).toTypedArray()
        activity?.setTitle(title)
        with(binding) {
            lifecycleOwner = activity
            challengeName.isEnabled = editMode.not()

            progress = StepProgress.values()
            steps = stepArray
            challenges = ChallengeType.values()
            viewmodel = this@AddOrEditChallengeFragment.viewModel

            challengeName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val item = ChallengeType.values()[position]
                    viewModel.loadChallengeProgress(item)
                }
            }
            activity?.let { act ->
                if (editMode) {
                    viewModel.loadChallenge(arguments?.getLong(Constants.CHALLENGE_ITEM_ID_KEY, -1) ?: -1)
                } else {
                    viewModel.challengeProgress.observe(act, challengeProgressObserver)
                    viewModel.loadChallengeProgress(ChallengeType.values()[0])
                }
                confirmChanges.setOnClickListener {
                    viewModel.challengeUpdate.observe(act, challengeUpdateObserver)
                    val challengeType = ChallengeType.values()[challengeName.selectedItemPosition]
                    val step = stepArray[challengeStep.selectedItemPosition]
                    val progress = StepProgress.values()[challengeStepProgress.selectedItemPosition]
                    val goal = challengeGoal.text.toString().toInt()
                    val series = challengeSeries.text.toString().toInt()
                    viewModel.applyChanges(challengeType, step, progress, goal, series)
                }
            }
        }
    }

    override fun deinitBinder() {
        viewModel.challengeUpdate.removeObserver(challengeUpdateObserver)
        viewModel.challengeProgress.removeObserver(challengeProgressObserver)
    }
}