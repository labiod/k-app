package com.kgb.k_app.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class ChallengeDBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ChallengeContract.ChallengeEntry.TABLE_NAME + " (" +
                    ChallengeContract.ChallengeEntry._ID + " INTEGER PRIMARY KEY," +
                    ChallengeContract.ChallengeEntry.COLUMN_NAME + " TEXT," +
                    ChallengeContract.ChallengeEntry.COLUMN_TYPE + " TEXT," +
                    ChallengeContract.ChallengeEntry.COLUMN_RATE + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ChallengeContract.ChallengeEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "challenge.db";

    public ChallengeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
