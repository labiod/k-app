package com.kgb.kapp.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ChallengeDB(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private val SQL_CREATE_ENTRIES = "CREATE TABLE ${ChallengeEntry.TABLE_NAME} (" +
            "${ChallengeEntry._ID} INTEGER PRIMARY KEY," +
            "${ChallengeEntry.TYPE_COL} TEXT," +
            "${ChallengeEntry.STEP_COL} INTEGER," +
            "${ChallengeEntry.STEP_PROGRESS_COL} TEXT" +
            ")"
        private val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${ChallengeEntry.TABLE_NAME}"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}