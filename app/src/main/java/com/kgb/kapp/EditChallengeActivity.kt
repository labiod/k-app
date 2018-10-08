package com.kgb.kapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import com.kgb.kapp.challenge.ChallengeType
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
    private lateinit var progressArray : Array<StepProgress>
    private lateinit var stepArray : Array<Int>
    private var editMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        editMode = intent.extras?.containsKey(Constants.CHALLENGE_ITEM_ID_KEY) ?: false
        initDataBinder()
        initViewModel()
    }

    private fun initDataBinder() {
        binding = DataBindingUtil.setContentView(this, R.layout.edit_challenge)
        val title = if (editMode) R.string.edit_challenge_title else R.string.new_challenge_title
        setTitle(title)
        binding.setLifecycleOwner(this)
        binding.editMode = editMode
        binding.challengeName.isEnabled = editMode.not()
        progressArray = StepProgress.values()
        val challenges = ChallengeType.values()
        stepArray = resources.getIntArray(R.array.challenge_step).toTypedArray()
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
            val challengeType = ChallengeType.values()[binding.challengeName.selectedItemPosition]
            val step = stepArray[binding.challengeStep.selectedItemPosition]
            val progress = StepProgress.values()[binding.challengeStepProgress.selectedItemPosition]
            val goal = binding.challengeGoal.text.toString().toInt()
            val series = binding.challengeSeries.text.toString().toInt()
            viewModel.applyChanges(challengeType, step, progress, goal, series)
            Toast.makeText(this@EditChallengeActivity, "Challenge changed", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(EditChallengeVieModel::class.java)
        viewModel.challengeProgress.observe(this, Observer { ch ->
            ch?.let {
                binding.challengeGoal.setText(it.goal.toString())
                binding.challengeSeries.setText(it.series.toString())
                binding.challengeStep.setSelection(it.step - 1)
                binding.challengeStepProgress.setSelection(it.stepProgress.ordinal)
            }

        })
        val id = intent.getLongExtra(Constants.CHALLENGE_ITEM_ID_KEY, -1)
        if (id != -1L) {
            viewModel.loadChallenge(id)
        } else {
            viewModel.loadChallengeProgress(ChallengeType.values()[0])
        }
        binding.viewmodel = viewModel
    }
}