package com.bitage.kapp.templatelist.ui

import android.content.Intent
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import com.bitage.kapp.R
import com.bitage.kapp.databinding.TemplateListItemBinding
import com.bitage.kapp.template.TemplateActivity
import com.bitage.kapp.templatelist.TemplateListViewModel
import io.reactivex.functions.Action

/**
 * Adapter class used in template screen provide view templates for recycler view
 */
class TemplateListAdapter(private val viewModel: TemplateListViewModel)
    : RecyclerView.Adapter<TemplateListAdapter.Holder>() {
    private val templates = viewModel.templates

    /**
     * Create holder for item defined as given position
     * @param parent - parent for view item
     * @param position - position of item on recycler view
     * @return [Holder] instance for given position
     */
    override fun onCreateViewHolder(parent: ViewGroup, position: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binder = DataBindingUtil.inflate<TemplateListItemBinding>(inflater, R.layout.template_list_item, parent, false)
        return Holder(binder)
    }

    /**
     * Get item count
     * @return item count
     */
    override fun getItemCount(): Int = templates.value?.size ?: 0

    /**
     * Method call when holder was bind
     * @param holder - holder that was bind
     * @param position - position of bound holder
     */
    override fun onBindViewHolder(holder: Holder, position: Int) {
        templates.value?.let { t ->
            val item = t[holder.adapterPosition]
            holder.binder.templateListItemName.text = item.templateName
            holder.binder.templateListEditItem.setOnClickListener {
                val intent = Intent(it.context, TemplateActivity::class.java)
                intent.putExtra(TemplateActivity.TEMPLATE_ID_KEY, item.id)
                it.context.startActivity(intent)
            }
            holder.binder.templateListDeleteItem.setOnClickListener {
                viewModel.deleteTemplate(item, Action {
                    Toast.makeText(holder.binder.root.context, "Item was delete", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }

    /**
     * Holder class for template adapter
     */
    class Holder(val binder: TemplateListItemBinding) : RecyclerView.ViewHolder(binder.root)
}