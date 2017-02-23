package com.kgb.k_app.model;

import android.content.ContentValues;

import com.kgb.k_app.data.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/22/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class StringData implements Data {
    String mData;

    public StringData(String data) {
        mData = data;
    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public ContentValues getData() {
        return null;
    }

    @Override
    public String getName() {
        return mData;
    }

    @Override
    public String getTableName() {
        return null;
    }

    public static List<StringData> asList(String[] source) {
        List<StringData> result = new ArrayList<>();
        for (String item : source) {
            result.add(new StringData(item));
        }
        return result;
    }
}
