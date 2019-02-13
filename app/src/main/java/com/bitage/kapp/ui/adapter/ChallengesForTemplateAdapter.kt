package com.bitage.kapp.ui.adapter

import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.R
import com.bitage.kapp.databinding.ChallengesForTemplateItemBinding

/**
 * Adapter class for challenges that will be added to template
 */
class ChallengesForTemplateAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<ChallengesForTemplateAdapter.Holder>() {
    /**
     * Holder class that keep all reference for given binder instance
     */
    class Holder(
        /**
         * view binder instance
         */
        val binder: ChallengesForTemplateItemBinding
    ) : androidx.recyclerview.widget.RecyclerView.ViewHolder(binder.root)

    /**
     * List of challenges added to template
     */
    val challenges: ArrayList<ChallengeType> = ArrayList()

    /**
     * Create view holder for given view type
     * @param parent - parent for created view
     * @param viewType - type of view
     * @return holder item for given type
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(DataBindingUtil.inflate(inflater, R.layout.challenges_for_template_item, parent, false))
    }

    /**
     * Count of item in adapter
     */
    override fun getItemCount() = challenges.size

    /**
     * Bind view holder for given position
     * @param holder - holder create by [ChallengesForTemplateAdapter.onCreateViewHolder] method
     */
    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binder.challenges = ChallengeType.values()
        holder.binder.selectChallenge = challenges[position]
        holder.binder.challengeName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") // To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                challenges[holder.adapterPosition] = ChallengeType.values()[pos]
            }
        }
        holder.binder.templateChallengeDeleteItem.setOnClickListener {
            challenges.removeAt(position)
            notifyDataSetChanged()
        }
    }

    /**
     * Add next challenge to template
     */
    fun addNext() {
        ChallengeType.values().forEach { ch ->
            if (!challenges.contains(ch)) {
                challenges.add(ch)
                return
            }
        }
        notifyDataSetChanged()
    }

    /**
     * Add all challenges to adapter
     * @param challenges - challenges to add
     */
    fun addAll(challenges: List<ChallengeType>) {
        this.challenges.clear()
        this.challenges.addAll(challenges)
        notifyDataSetChanged()
    }
}