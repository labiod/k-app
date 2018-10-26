package com.kgb.kapp.components

import android.arch.lifecycle.LiveData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kgb.kapp.R
import com.kgb.kapp.db.entity.TemplateEntity

/**
 * Adapter for template entity
 */
class TemplatesDialogAdapter(private val data: LiveData<List<TemplateEntity>>) : BaseAdapter() {
    /**
     * Get view for current position
     * @param position - current position
     * @param convertView - cache view
     * @param parent - parent for view
     * @return View for given adapter item
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val result = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.template_dialog_item, parent, false)
        val text = result.findViewById<TextView>(R.id.template_name)
        text.text = getItem(position)?.templateName ?: ""
        return result
    }

    /**
     * Get item for given position
     * @return template item for given position
     */
    override fun getItem(position: Int) = data.value?.get(position)

    /**
     * Get item id for given position
     * @param position - adapter position
     * @return id for item
     */
    override fun getItemId(position: Int) = data.value?.get(position)?.id ?: 0

    /**
     * Count of item adapter
     * @return number of item
     */
    override fun getCount() = data.value?.size ?: 0
}