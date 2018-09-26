package com.kgb.kapp.data

import android.content.ContentValues
import android.provider.BaseColumns

/**
 * @author Krzysztof Betlej <kgbetlej></kgbetlej>@gmail.com>.
 * @date 2/22/17
 * @copyright Copyright (c)2017 BitAge
 */

interface Data {

    val id: Long

    val name: String

    fun getTableName(): String

    fun getIdColumnName() : String

    fun getData(): ContentValues

    interface DataEntry : BaseColumns
}