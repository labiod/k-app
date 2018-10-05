package com.kgb.kapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.challenge.StepProgress
import com.kgb.kapp.databinding.EditChallengeBinding
import com.kgb.kapp.viewmodel.EditChallengeVieModel
import kotlinx.android.synthetic.main.component_list_item.*
import kotlinx.android.synthetic.main.edit_challenge.*

/**
 * This activity is a setting screen for choose challenge
 * It's bind layout [R.layout.edit_challenge]
 */
class EditChallengeActivity : AppCompatActivity() {
    private lateinit var viewModel : EditChallengeVieModel
    private lateinit var binding : EditChallengeBinding
    private lateinit var progressArray : Array<String>
    private lateinit var stepArray : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDataBinder()
        initViewModel()
    }

    private fun initDataBinder() {
        binding = DataBindingUtil.setContentView(this, R.layout.edit_challenge)
        binding.setLifecycleOwner(this)
        progressArray = arrayOf(
            getString(StepProgress.BEGINNER.levelTextRes),
            getString(StepProgress.ADVANCE.levelTextRes),
            getString(StepProgress.PROGRESSION.levelTextRes),
            getString(StepProgress.CUSTOM.levelTextRes)
        )
        stepArray = resources.getStringArray(R.array.challenge_step)
        binding.steps = progressArray

        binding.challengeStep.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.adapter?.getItem(position).toString().toInt()
                Toast.makeText(this@EditChallengeActivity, "step change to: $item", Toast.LENGTH_SHORT).show()
            }
        }

        binding.challengeStepProgress.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val item = parent?.adapter?.getItem(position)
                Toast.makeText(this@EditChallengeActivity, "progress was changed to: $item", Toast.LENGTH_SHORT).show()
            }
        }
        confirm_changes.setOnClickListener {
            val step = stepArray[binding.challengeStep.selectedItemPosition].toInt()
            val progress = StepProgress.values()[binding.challengeStepProgress.selectedItemPosition]
            val goal = binding.challengeGoal.text.toString().toInt()
            val series = binding.challengeSeries.text.toString().toInt()
            viewModel.applyChanges(step, progress, goal, series)
            Toast.makeText(this@EditChallengeActivity, "Challenge changed", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditChallengeVieModel::class.java)
        viewModel.challenge.observe(this, Observer { challenge ->
            challenge?.let {
                binding.challengeName.text = getString(it.challengeName.challengeResId)
                challenge_step_progress.setSelection(it.progress.id)
                challenge_step.setSelection(it.step - 1)
            }
        })
        val id = intent.getIntExtra(Constants.CHALLENGE_ITEM_ID_KEY, -1)
        if (id != -1) {
            viewModel.loadChallenge(id)
        }
    }
}