package com.kgb.kapp.components

import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.kgb.kapp.R

class ComponentsAdapter(val components: List<ComponentDAO>) : RecyclerView.Adapter<ComponentsAdapter.Holder>() {
    class Holder(root: View) : RecyclerView.ViewHolder(root) {
        val nameView: TextView = root.findViewById(R.id.component_name)
        val finishedButton: ImageButton = root.findViewById(R.id.component_finished_button)
        val currentGoalView: TextView = root.findViewById(R.id.current_goal)
        val currentStepView: TextView = root.findViewById(R.id.component_level)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.component_list_item, parent, false))
    }

    override fun getItemCount() = components.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val context = holder.itemView.context
        val item = components[position]
        if (item.finished) {
            holder.finishedButton.isEnabled = !components[position].finished
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }
        holder.finishedButton.setOnClickListener {
            item.finished = true
            notifyDataSetChanged()
        }
        holder.nameView.setText(components[position].challengeType.challengeName)

        holder.currentGoalView.text = context.getString(R.string.challenge_current_goal_format, components[position].goal, components[position].series)
        holder.currentStepView.text = context.getString(R.string.challenge_current_level_format, components[position].step, ComponentDAO.MAX_LEVEL)
    }

    override fun getItemViewType(position: Int): Int {
        if (components[position].finished) {
            return 1
        } else {
            return 0
        }
    }


}