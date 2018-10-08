package com.kgb.kapp.components

import android.arch.lifecycle.LiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.kgb.kapp.R
import com.kgb.kapp.db.entity.TemplateEntity

class TemplatesDialogAdapter(val data: LiveData<List<TemplateEntity>>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val result = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.template_dialog_item, parent, false)
        val text = result.findViewById<TextView>(R.id.template_name)
        text.text = getItem(position)?.templateName ?: ""
        return result
    }

    override fun getItem(position: Int) = data.value?.get(position)

    override fun getItemId(position: Int) = data.value?.get(position)?.id ?: 0

    override fun getCount() = data.value?.size ?: 0
}