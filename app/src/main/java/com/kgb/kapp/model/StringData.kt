package com.kgb.kapp.model

import android.content.ContentValues
import com.kgb.kapp.data.AbstractData
import java.util.ArrayList

/**
 * @author Krzysztof Betlej <kgbetlej></kgbetlej>@gmail.com>.
 * @date 2/22/17
 * @copyright Copyright (c) 2017 BitAge
 */

class StringData(override val name: String) : AbstractData() {

    override val id: Long
        get() = 0

    override fun  getTableName(): String {
        return ""
    }

    override fun getData(): ContentValues {
        val result = ContentValues()
        return result
    }

    companion object {
        @JvmStatic
        fun asList(source: Array<String>): List<StringData> {
            val result = ArrayList<StringData>()
            for (item in source) {
                result.add(StringData(item))
            }
            return result
        }
    }
}