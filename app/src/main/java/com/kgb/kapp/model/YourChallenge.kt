package com.kgb.kapp.model

import android.content.ContentValues
import android.database.Cursor
import com.kgb.kapp.data.AbstractData
import com.kgb.kapp.database.ChallengeContract
import java.util.*

/**
 * @author Krzysztof Betlej <kgbetlej></kgbetlej>@gmail.com>.
 * @date 2/24/17
 * @copyright Copyright (c) 2017 BitAge
 */

class YourChallenge : AbstractData {
    private var mChallenge: Challenge? = null
    var startDate: Date? = null
    var expireDate: Date? = null
    val progress: Int = 0
    override var id: Long = -1

    override val name: String
        get() = if (mChallenge != null) mChallenge!!.name else ""

    val maxProgress: Int
        get() = mChallenge!!.rate

    constructor(challenge: Challenge) {
        mChallenge = challenge
    }

    constructor(cursor: Cursor) {
        id = cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry._ID))
        mChallenge = Challenge(cursor)
        startDate = Date(cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry.COLUMN_START_DATE)))
        expireDate = Date(cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE)))
    }

    override fun getData(): ContentValues {
        val values = ContentValues()
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_CHALLENGE_ID, mChallenge!!.id)
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_START_DATE, startDate!!.time)
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE, expireDate!!.time)
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_PROGRESS, expireDate!!.time)
        return values
    }

    override fun getTableName(): String {
        return ChallengeContract.YourChallengeEntry.TABLE_NAME
    }
}