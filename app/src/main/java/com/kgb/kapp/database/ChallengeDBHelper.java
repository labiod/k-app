package com.kgb.kapp.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/21/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public class ChallengeDBHelper extends SQLiteOpenHelper {
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + ChallengeContract.ChallengeEntry.TABLE_NAME + " (" +
                    ChallengeContract.ChallengeEntry._ID + " INTEGER PRIMARY KEY," +
                    ChallengeContract.ChallengeEntry.COLUMN_NAME + " TEXT," +
                    ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION + " TEXT," +
                    ChallengeContract.ChallengeEntry.COLUMN_TYPE + " INTEGER," +
                    ChallengeContract.ChallengeEntry.COLUMN_RATE + " INTEGER)";

    private static final String SQL_CREATE_ENTRIES2 =
            "CREATE TABLE " + ChallengeContract.YourChallengeEntry.TABLE_NAME + " (" +
                    ChallengeContract.YourChallengeEntry._ID + " INTEGER PRIMARY KEY," +
                    ChallengeContract.YourChallengeEntry.COLUMN_CHALLENGE_ID + " INTEGER," +
                    ChallengeContract.YourChallengeEntry.COLUMN_START_DATE + " INTEGER," +
                    ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE + " INTEGER," +
                    ChallengeContract.YourChallengeEntry.COLUMN_PROGRESS + " INTEGER)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ChallengeContract.ChallengeEntry.TABLE_NAME;

    private static final String SQL_DELETE_ENTRIES2 =
            "DROP TABLE IF EXISTS " + ChallengeContract.YourChallengeEntry.TABLE_NAME;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "challenges.db";

    public ChallengeDBHelper(Context context) {
        this(context, null);
    }

    public ChallengeDBHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        db.execSQL(SQL_DELETE_ENTRIES2);
        onCreate(db);
    }
}
