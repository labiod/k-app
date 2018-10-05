package com.kgb.kapp.components

import android.content.Intent
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kgb.kapp.EditChallengeActivity
import com.kgb.kapp.R
import com.kgb.kapp.challenge.Constants
import com.kgb.kapp.challenge.Constants.Companion.MAX_LEVEL
import com.kgb.kapp.databinding.ComponentListItemBinding
import com.kgb.kapp.db.ChallengeEntity
import com.kgb.kapp.viewmodel.TodayChallengeViewModel

class TodayChallengesAdapter(private val challengesModel : TodayChallengeViewModel)
    : RecyclerView.Adapter<TodayChallengesAdapter.Holder>() {
    class Holder(val binding: ComponentListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.component_list_item, parent, false))
    }

    override fun getItemCount() = challengesModel.challenges.value?.size ?: 0

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val context = holder.itemView.context
        val item = getItem(position)
        if (item.finished) {
            holder.binding.challengeEditButton.visibility = View.GONE
            holder.binding.challengeDeleteButton.visibility = View.GONE
            holder.binding.challengeFinishedButton.setImageResource(R.drawable.ic_challenge_revert)
            holder.itemView.setBackgroundColor(Color.LTGRAY)
        }
        holder.binding.challengeFinishedButton.setOnClickListener {
            challengesModel.updateChallenge(item.setFinishChallenge(item.finished.not()))

        }

        holder.binding.challengeEditButton.setOnClickListener {
            val intent = Intent(it.context, EditChallengeActivity::class.java)
            intent.putExtra(Constants.CHALLENGE_ITEM_ID_KEY, item.id)
            it.context.startActivity(intent)
        }

        holder.binding.challengeDeleteButton.setOnClickListener {
            challengesModel.deleteChallenge(item)
        }

        holder.binding.challengeName.setText(item.challengeName.challengeResId)

        holder.binding.currentGoal.text = context.resources.getQuantityString(
            R.plurals.challenge_current_goal_format, item.series, item.goal, item.series)
        holder.binding.stepLevel.text = context.getString(
            R.string.challenge_current_level_format, item.step, MAX_LEVEL)
    }

    override fun getItemViewType(position: Int) = if (getItem(position).finished) 1 else 0

    private fun getItem(position: Int) : ChallengeEntity {
        return challengesModel.challenges.value!![position]
    }
}