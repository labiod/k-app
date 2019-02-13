package com.bitage.kapp.templatelist

import androidx.lifecycle.Observer
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.bitage.kapp.R
import com.bitage.kapp.databinding.ActivityTemplateListBinding
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.templatelist.ui.TemplateListAdapter

/**
 * View class for template list screen
 */
class KAppTemplateListView(private val activity: TemplateListActivity) : TemplateListView {
    private lateinit var binding: ActivityTemplateListBinding
    private lateinit var viewModel: TemplateListViewModel
    private var adapter: TemplateListAdapter? = null

    override fun onCreate() {
        binding = DataBindingUtil.setContentView(activity, R.layout.activity_template_list)
    }

    override fun onDestroy() {
        binding.unbind()
    }

    override fun androidView(): View = binding.root

    override fun attachViewModel(viewModel: TemplateListViewModel) {
        this.viewModel = viewModel
        initView()
    }

    private fun initView() {
        adapter = TemplateListAdapter(viewModel)
        val lm = androidx.recyclerview.widget.LinearLayoutManager(activity, androidx.recyclerview.widget.RecyclerView.VERTICAL, false)
        binding.template.layoutManager = lm
        binding.template.adapter = adapter
        viewModel.templates.observe(activity, Observer {
            adapter?.notifyDataSetChanged()
        })
        binding.addNewTemplateFab.setOnClickListener {
            val intent = Intent(activity, TemplateActivity::class.java)
            activity.startActivity(intent)
        }
    }
}