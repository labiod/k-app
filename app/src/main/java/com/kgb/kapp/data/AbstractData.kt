package com.kgb.kapp.data

import android.provider.BaseColumns

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 3/6/17
 * @copyright Copyright (c)2017 KGBetlej
 */

abstract class AbstractData : Data {
    override fun getIdColumnName(): String {
        return BaseColumns._ID
    }
}