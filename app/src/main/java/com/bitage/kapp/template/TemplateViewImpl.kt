package com.bitage.kapp.template

import android.arch.lifecycle.Observer
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
    private val templateObserver = Observer<Template> {
        it?.let { t ->
            adapter.addAll(t.challenges)
        }
    }

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

    override fun attachViewModel(viewModel: TemplateViewModel) {
        model = viewModel
        binding.viewmodel = model
        binding.setLifecycleOwner(activity)
        adapter = ChallengesForTemplateAdapter()
        binding.challengesList.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        binding.challengesList.adapter = adapter
        model.template.observe(activity, templateObserver)
        binding.addNextChallenge.setOnClickListener {
            addNewChallenge()
        }
        binding.confirmChanges.setOnClickListener {
            model.template.value?.let {
                updateTemplate(it)
            } ?: createTemplate()
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
        model.createOrUpdateTemplate(template, Action {
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

    private fun updateTemplate(template: Template) {
        val newTemplate = Template(template.id, binding.templateName.text.toString())
        newTemplate.challenges.addAll(adapter.challenges)
        model.template.removeObserver(templateObserver)
        model.createOrUpdateTemplate(newTemplate, Action {
            activity.runOnUiThread {
                Toast.makeText(activity, "Item edited", Toast.LENGTH_SHORT).show()
                activity.finish()
            }
        }, Consumer {
            activity.runOnUiThread {
                Toast.makeText(activity, "Problem with edit template. Check logs form more details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}