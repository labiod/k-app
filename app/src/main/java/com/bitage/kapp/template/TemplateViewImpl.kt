package com.bitage.kapp.template

import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.bitage.kapp.R
import com.bitage.kapp.ui.adapter.ChallengesForTemplateAdapter
import com.bitage.kapp.databinding.ActivityTemplateBinding
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.Template
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Implementation of view for template screen
 */
class TemplateViewImpl(private val activity: TemplateActivity) : TemplateView {

    private lateinit var binding: ActivityTemplateBinding

    private lateinit var adapter: ChallengesForTemplateAdapter
    private lateinit var model: TemplateViewModel

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_template)
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
     * Init view with view model
     * @param model - view model for template screen
     */
    override fun initView(model: TemplateViewModel) {
        this.model = model
        adapter = ChallengesForTemplateAdapter()
        binding.challengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.challengesList.adapter = adapter
        binding.addNextChallenge.setOnClickListener {
            addNewChallenge()
        }
        binding.confirmChanges.setOnClickListener {
            createTemplate()
        }
    }

    private fun addNewChallenge() {
        adapter.addNext()
        if (adapter.challenges.size == ChallengeType.values().size) {
            binding.addNextChallenge.isEnabled = false
        }
    }

    private fun createTemplate() {
        val template = Template(null, binding.templateName.text.toString())
        template.challenges.addAll(adapter.challenges)
        model.createTemplate(template, Action {
            activity.runOnUiThread {
                Toast.makeText(activity, "New item added", Toast.LENGTH_SHORT).show()
                activity.finish()
            }
        }, Consumer {
            activity.runOnUiThread {
                Toast.makeText(activity, "Problem with adding template to Database. Check logs form more details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}