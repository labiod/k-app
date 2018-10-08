package com.kgb.kapp.components

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.kgb.kapp.R
import com.kgb.kapp.challenge.ChallengeType
import com.kgb.kapp.databinding.ChallengesForTemplateItemBinding

class ChallengesForTemplateAdapter : RecyclerView.Adapter<ChallengesForTemplateAdapter.Holder>() {
    class Holder(val binder: ChallengesForTemplateItemBinding) : RecyclerView.ViewHolder(binder.root)

    val challenges: ArrayList<ChallengeType> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        return Holder(DataBindingUtil.inflate(inflater, R.layout.challenges_for_template_item, parent, false))
    }

    override fun getItemCount() = challenges.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.binder.challenges = ChallengeType.values()
        holder.binder.selectChallenge = challenges[position]
        holder.binder.challengeName.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
                challenges[position] = ChallengeType.values()[pos]
            }
        }
    }

    fun addNext() {
        val nextChallenges = ChallengeType.values()
        for (ch in nextChallenges) {
            if (!challenges.contains(ch)) {
                challenges.add(ch)
                break;
            }
        }
        notifyDataSetChanged()
    }
}