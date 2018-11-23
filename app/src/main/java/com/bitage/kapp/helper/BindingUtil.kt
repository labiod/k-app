package com.bitage.kapp.helper

import android.content.Context
import android.databinding.BindingAdapter
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.bitage.kapp.model.ChallengeType
import com.bitage.kapp.challenge.ChallengeTypeResMapper

/**
 * Util class for data binding
 */
class BindingUtil {
    companion object {
        /**
         * Get index for given challenge type
         * @param challengeType - given challenge
         * @return index of give
         */
        @JvmStatic
        fun getIndexForChallenge(challengeType: ChallengeType?) = challengeType?.ordinal ?: 0

        /**
         * Select given item in current spinner
         * @param spinner - given view
         * @param item - given item
         */
        @JvmStatic
        @BindingAdapter("selectItem")
        fun setSelectItem(spinner: Spinner, item: Any?) {
            spinner.adapter?.let {
                for (i in 0 until spinner.adapter.count) {
                    if (spinner.adapter.getItem(i) == item) {
                        spinner.setSelection(i)
                    }
                }
            }
        }

        /**
         * Bind given data to current spinner
         * @param spinner - given spinner
         * @param data - list of item that will be bind to spinner
         */
        @JvmStatic
        @BindingAdapter("bindData")
        fun bindData(spinner: Spinner, data: Array<Any>) {
            val adapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_dropdown_item, data)
            spinner.adapter = adapter
        }

        /**
         * Convert challenges array to proper strings base on lang
         * @param challenges - challenges to convert
         * @param context - context object
         * @return list of challenges name
         */
        @JvmStatic
        fun convertChallengesArrayToStrings(challenges: Array<ChallengeType>, context: Context): ArrayList<String> {
            val result = ArrayList<String>()
            challenges.forEach {
                result.add(context.getString(ChallengeTypeResMapper.valueOf(it.name).resId))
            }
            return result
        }
    }
}