package com.kgb.kapp.helper

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.kgb.kapp.challenge.ChallengeType

class BindingUtil {
    companion object {
        @JvmStatic
        fun getIndexForChallenge(challengeType: ChallengeType?): Int {
            val challenges = ChallengeType.values()
            for( ch in challenges) {
                if (ch == challengeType) {
                    return ch.ordinal
                }
            }
            return 0
        }

        @JvmStatic
        fun getChallengeStepIndex(step: Int): Int {
            return 0
        }

        @JvmStatic
        @BindingAdapter("selectItem")
        fun setSelectItem(spinner: Spinner, item: Any?) {
            spinner.adapter?.let {
                for( i in 0 until spinner.adapter.count) {
                    if (spinner.adapter.getItem(i) == item) {
                        spinner.setSelection(i)
                    }
                }
            }
        }

        @JvmStatic
        @BindingAdapter("bindData")
        fun bindData(spinner: Spinner, data: Array<Any>) {
            val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_dropdown_item, data)
            spinner.adapter = adapter
        }

        @JvmStatic
        fun convertChallengesArrayToStrings(challenges: Array<ChallengeType>, context: Context): ArrayList<String> {
            val result = ArrayList<String>()
            challenges.forEach {
                result.add(context.getString(it.challengeResId))
            }
            return result
        }
    }
}