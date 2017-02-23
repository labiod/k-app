package com.kgb.k_app.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kgb.k_app.data.Data;

import java.util.Locale;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class DBConnection {
    public static final int READ_MODE = 1;
    public static final int WRITE_MODE = 2;
    private SQLiteOpenHelper mDBHelper;
    public DBConnection(SQLiteOpenHelper dbHelper) {
        mDBHelper = dbHelper;
    }

    private SQLiteDatabase connect(int mode) {
        switch (mode) {
            case READ_MODE:
                return mDBHelper.getReadableDatabase();
            case WRITE_MODE:
                return mDBHelper.getWritableDatabase();
            default:
                return null;
        }
    }

    public void update(Data data) {
        SQLiteDatabase db = connect(WRITE_MODE);
        db.update(data.getTableName(), data.getData(), String.format(Locale.ENGLISH, "%s = %d", Data.DataEntry._ID, data.getId()), null);
        db.close();
    }

    public void insert(Data data) {
        SQLiteDatabase db = connect(WRITE_MODE);
        db.insert(data.getTableName(), null, data.getData());
        db.close();
    }
}
