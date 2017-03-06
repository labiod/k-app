package com.kgb.k_app.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.kgb.k_app.data.BaseData;
import com.kgb.k_app.data.Data;
import com.kgb.k_app.database.ChallengeContract;

import java.util.Date;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/24/17
 * @copyright Copyright (c) 2017 KGBetlej
 */

public class YourChallenge extends BaseData {
    private Challenge mChallenge;
    private Date mStartDate;
    private Date mExpireDate;
    private long mId = -1;

    public YourChallenge(Challenge challenge) {
        mChallenge = challenge;
    }

    public YourChallenge(Cursor cursor) {
        mId = cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry._ID));
        mChallenge = new Challenge(cursor);
        mStartDate = new Date(cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry.COLUMN_START_DATE)));
        mExpireDate = new Date(cursor.getLong(cursor.getColumnIndex(ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE)));
    }


    @Override
    public long getId() {
        return mId;
    }

    @Override
    public ContentValues getData() {
        ContentValues values = new ContentValues();
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_CHALLENGE_ID, mChallenge.getId());
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_START_DATE, mStartDate.getTime());
        values.put(ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE, mExpireDate.getTime());
        return values;
    }

    @Override
    public String getName() {
        return mChallenge != null ? mChallenge.getName() : "";
    }

    @Override
    public String getTableName() {
        return ChallengeContract.YourChallengeEntry.TABLE_NAME;
    }

    public void setStartDate(Date startDate) {
        mStartDate = startDate;
    }

    public void setExpireDate(Date expireDate) {
        mExpireDate = expireDate;
    }

    public void setId(long id) {
        mId = id;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public Date getExpireDate() {
        return mExpireDate;
    }
}
