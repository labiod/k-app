package com.kgb.k_app.data;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/22/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public interface Data {
    interface DataEntry extends BaseColumns {

    }

    long getId();

    String getIdColumnName();

    ContentValues getData();

    String getName();

    String getTableName();
}
