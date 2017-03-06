package com.kgb.k_app.data;

import android.provider.BaseColumns;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 3/6/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public abstract class BaseData implements Data {

    @Override
    public String getIdColumnName() {
        return BaseColumns._ID;
    }
}
