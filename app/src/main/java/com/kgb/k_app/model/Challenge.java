package com.kgb.k_app.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.kgb.k_app.data.BaseData;
import com.kgb.k_app.data.Data;
import com.kgb.k_app.database.ChallengeContract;

import java.util.Date;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/21/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public class Challenge extends BaseData {
    private String mChallengeName;
    private String mChallengeDescription;
    private int mChallengeRate;
    private int mChallengeType;
    private long mId = -1;

    public Challenge(Cursor cursor) {
        mId = cursor.getLong(cursor.getColumnIndex(ChallengeContract.ChallengeEntry._ID));
        mChallengeName = cursor.getString(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_NAME));
        mChallengeDescription = cursor.getString(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION));
        mChallengeRate = cursor.getInt(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_RATE));
        mChallengeType = cursor.getInt(cursor.getColumnIndex(ChallengeContract.ChallengeEntry.COLUMN_TYPE));
    }

    public Challenge() {

    }

    @Override
    public long getId() {
        return mId;
    }

    @Override
    public ContentValues getData() {
        ContentValues values = new ContentValues();
        values.put(ChallengeContract.ChallengeEntry.COLUMN_NAME, mChallengeName);
        values.put(ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION, mChallengeDescription);
        values.put(ChallengeContract.ChallengeEntry.COLUMN_RATE, mChallengeRate);
        values.put(ChallengeContract.ChallengeEntry.COLUMN_TYPE, mChallengeType);
        return values;
    }

    @Override
    public String getName() {
        return mChallengeName;
    }

    @Override
    public String getTableName() {
        return ChallengeContract.ChallengeEntry.TABLE_NAME;
    }

    public static String[] getColumns() {
        return new String[] {
                ChallengeContract.ChallengeEntry._ID,
                ChallengeContract.ChallengeEntry.COLUMN_NAME,
                ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION,
                ChallengeContract.ChallengeEntry.COLUMN_RATE,
                ChallengeContract.ChallengeEntry.COLUMN_TYPE
        };
    }

    public void setName(String name) {
        mChallengeName = name;
    }

    public void setType(int type) {
        mChallengeType = type;
    }

    public void setRate(int rate) {
        mChallengeRate = rate;
    }
}
