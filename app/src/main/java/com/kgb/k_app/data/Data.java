package com.kgb.k_app.data;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/22/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public interface Data {
    int getId();

    interface DataEntry extends BaseColumns {

    }
    ContentValues getData();

    String getName();

    String getTableName();
}
