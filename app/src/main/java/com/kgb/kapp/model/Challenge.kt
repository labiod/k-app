package com.kgb.kapp.model

import android.content.ContentValues
import android.database.Cursor
import com.kgb.kapp.data.AbstractData
import com.kgb.kapp.database.ChallengeContract

/**
 * @author Krzysztof Betlej <kgbetlej></kgbetlej>@gmail.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2017 BitAge
 */

class Challenge(override val name: String, val rate: Int, val type: Int) : AbstractData() {
    private var mChallengeDescription: String = ""
    override var id: Long = 0

    constructor(cursor: Cursor) : this(
            cursor.getString(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_NAME)),
            cursor.getInt(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_RATE)),
            cursor.getInt(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_TYPE))) {
        mChallengeDescription = cursor.getString(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION))
        id = cursor.getLong(cursor.getColumnIndex(ChallengeContract.ChallengeEntry._ID))
    }

    override fun getData(): ContentValues {
        val values = ContentValues()
        values.put(ChallengeContract.ChallengeEntry.COLUMN_NAME, name)
        values.put(ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION, mChallengeDescription)
        values.put(ChallengeContract.ChallengeEntry.COLUMN_RATE, rate)
        values.put(ChallengeContract.ChallengeEntry.COLUMN_TYPE, type)
        return values
    }

    override fun getTableName(): String {
        return ChallengeContract.ChallengeEntry.TABLE_NAME
    }

    companion object {
        @JvmStatic
        val columns: Array<String>
            get() = arrayOf(
                    ChallengeContract.ChallengeEntry._ID,
                    ChallengeContract.ChallengeEntry.COLUMN_NAME,
                    ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION,
                    ChallengeContract.ChallengeEntry.COLUMN_RATE,
                    ChallengeContract.ChallengeEntry.COLUMN_TYPE
                )
    }
}