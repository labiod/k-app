package com.bitage.kapp.template

import androidx.lifecycle.Observer
import androidx.databinding.DataBindingUtil
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.ui.adapter.ChallengesForTemplateAdapter
import com.bitage.kapp.databinding.ActivityTemplateBinding
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.model.Template
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * Implementation of view for template screen
 */
class TemplateViewImpl : TemplateView {

    private lateinit var binding: ActivityTemplateBinding

    private lateinit var adapter: ChallengesForTemplateAdapter
    private lateinit var model: TemplateViewModel
    private lateinit var screen: Screen
    private val templateObserver = Observer<Template> {
        it?.let { t ->
            adapter.addAll(t.challenges)
        }
    }

    /**
     * Controls lifecycle of this view. It should be called in presenter onCreate method
     */
    override fun onCreate() {
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
        binding = DataBindingUtil.setContentView(screen.getActivity(), R.layout.activity_template)
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
        binding.setLifecycleOwner(screen)
        adapter = ChallengesForTemplateAdapter()
        binding.challengesList.layoutManager = LinearLayoutManager(screen.getActivity(), RecyclerView.VERTICAL, false)
        binding.challengesList.adapter = adapter
        model.template.observe(screen, templateObserver)
        binding.addNextChallenge.setOnClickListener {
            addNewChallenge()
        }
        binding.confirmChanges.setOnClickListener {
            model.template.value?.let {
                updateTemplate(it)
            } ?: createTemplate()
        }
    }

    override fun customizeActionBar(actionBar: ActionBar?) {}

    private fun addNewChallenge() {
        adapter.addNext()
        if (adapter.challenges.size == ChallengeType.values().size) {
            binding.addNextChallenge.isEnabled = false
        }
        adapter.notifyDataSetChanged()
    }

    private fun createTemplate() {
        val template = Template(null, binding.templateName.text.toString())
        template.challenges.addAll(adapter.challenges)
        model.createOrUpdateTemplate(template, Action {
            screen.runOnUi {
                Toast.makeText(screen.getActivity(), "New item added", Toast.LENGTH_SHORT).show()
                screen.finish()
            }
        }, Consumer {
            screen.runOnUi {
                Toast.makeText(screen.getActivity(), "Problem with adding template to Database. Check logs form more details", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun updateTemplate(template: Template) {
        val newTemplate = Template(template.id, binding.templateName.text.toString())
        newTemplate.challenges.addAll(adapter.challenges)
        model.template.removeObserver(templateObserver)
        model.createOrUpdateTemplate(newTemplate, Action {
            screen.runOnUi {
                Toast.makeText(screen.getActivity(), "Item edited", Toast.LENGTH_SHORT).show()
                screen.finish()
            }
        }, Consumer {
            screen.runOnUi {
                Toast.makeText(screen.getActivity(), "Problem with edit template. Check logs form more details", Toast.LENGTH_SHORT).show()
            }
        })
    }
}