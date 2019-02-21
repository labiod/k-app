package com.bitage.kapp.templatelist

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import androidx.appcompat.app.ActionBar
import com.bitage.kapp.R
import com.bitage.kapp.Screen
import com.bitage.kapp.databinding.ActivityTemplateListBinding
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.templatelist.ui.TemplateListAdapter

/**
 * View class for template list screen
 */
class KAppTemplateListView : TemplateListView {
    private val binding: ActivityTemplateListBinding by lazy {
        DataBindingUtil.setContentView<ActivityTemplateListBinding>(screen.getActivity(), R.layout.activity_template_list)
    }
    private lateinit var viewModel: TemplateListViewModel
    private lateinit var screen: Screen
    private var adapter: TemplateListAdapter? = null

    override fun onCreate() {
    }

    override fun onDestroy() {
        binding.unbind()
    }

    override fun onAttached(screen: Screen) {
        this.screen = screen
    }

    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: TemplateListViewModel) {
        this.viewModel = viewModel
        initView()
    }

    override fun customizeActionBar(actionBar: ActionBar?) {}

    private fun initView() {
        adapter = TemplateListAdapter(viewModel)
        val lm = LinearLayoutManager(screen.getActivity(), RecyclerView.VERTICAL, false)
        binding.template.layoutManager = lm
        binding.template.adapter = adapter
        viewModel.templates.observe(screen, Observer {
            adapter?.notifyDataSetChanged()
        })
        binding.addNewTemplateFab.setOnClickListener {
            val intent = Intent(screen.getActivity(), TemplateActivity::class.java)
            screen.startActivity(intent)
        }
    }
}