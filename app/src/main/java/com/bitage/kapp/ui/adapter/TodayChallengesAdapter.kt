package com.bitage.kapp.ui.adapter

import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bitage.kapp.model.Challenge
import com.bitage.kapp.editchallenge.EditChallengeActivity
import com.bitage.kapp.presentation.Constants
import com.bitage.kapp.presentation.Constants.Companion.MAX_LEVEL
import com.bitage.kapp.R
import com.bitage.kapp.databinding.ComponentListItemBinding
import com.bitage.kapp.challenge.ChallengeTypeResMapper
import com.bitage.kapp.daychallenges.DayChallengeViewModel
import com.bitage.kapp.daychallenges.OnChallengeActionListener

/**
 * Adapter for list of challenges for given date
 * This class used challengesModel (see [DayChallengeViewModel]) to retrieve data from repository
 * and show them into RecyclerView
 */
class TodayChallengesAdapter(private val challengesModel: DayChallengeViewModel)
    : androidx.recyclerview.widget.RecyclerView.Adapter<TodayChallengesAdapter.Holder>() {
    /**
     * Holder class that keep all references to views from given layout.
     */
    class Holder(
        /**
         * binding object
         */
        val binding: ComponentListItemBinding
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binding.root)

    private var listener: OnChallengeActionListener? = null
    /**
     * Create holder for recycler view item
     * @param parent - parent for all views
     * @param viewType - type of created item (see [getItemViewType]
     * @return instance of [Holder] class
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.component_list_item, parent, false))
    }

    /**
     * get count of items in adapter
     * @return number of item in adapter
     */
    override fun getItemCount() = challengesModel.challenges.value?.size ?: 0

    fun setOnChallengeActionListener(listener: OnChallengeActionListener) {
        this.listener = listener
    }

    /**
     * Bind holder for given adapter position
     * @param holder - holder created by [onCreateViewHolder] method
     * @param position - current position on adapter
     */
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val context = holder.itemView.context
        getItem(position)?.let { item ->
            if (item.finished) {
                holder.binding.challengeEditButton.visibility = View.GONE
                holder.binding.challengeDeleteButton.visibility = View.GONE
                holder.binding.challengeFinishedButton.setImageResource(R.drawable.ic_challenge_revert)
                holder.itemView.setBackgroundColor(Color.LTGRAY)
            }
            holder.binding.challengeFinishedButton.setOnClickListener {
                listener?.onChallengeFinish(item.setFinishChallenge(item.finished.not()))
            }

            holder.binding.challengeEditButton.setOnClickListener {
                val intent = Intent(it.context, EditChallengeActivity::class.java)
                intent.putExtra(Constants.CHALLENGE_ITEM_ID_KEY, item.id)
                it.context.startActivity(intent)
            }

            holder.binding.challengeDeleteButton.setOnClickListener {
                listener?.onChallengeDelete(item)
            }

            holder.binding.challengeName.setText(ChallengeTypeResMapper.valueOf(item.challengeName.name).resId)

            holder.binding.currentGoal.text = context.resources.getQuantityString(
                R.plurals.challenge_current_goal_format, item.series, item.goal, item.series)
            holder.binding.stepLevel.text = context.getString(
                R.string.challenge_current_level_format, item.step, MAX_LEVEL)
        }
    }

    /**
     * Get item type for current position
     * @param position - given adapter position
     * @return 1 if item is finished or 0 if not
     */
    override fun getItemViewType(position: Int) = if (getItem(position)?.finished == true) 1 else 0

    private fun getItem(position: Int): Challenge? = challengesModel.challenges.value?.get(position)
}