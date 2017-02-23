package com.kgb.k_app.model;

import android.content.ContentValues;

import com.kgb.k_app.data.Data;
import com.kgb.k_app.database.ChallengeContract;

import java.util.Date;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class Challenge implements Data {
    private String mChallengeName;
    private int mChallengeRate;
    private String mChallengeType;

    @Override
    public ContentValues getData() {
        ContentValues values = new ContentValues();
        values.put(ChallengeContract.ChallengeEntry.COLUMN_NAME, mChallengeName);
        values.put(ChallengeContract.ChallengeEntry.COLUMN_RATE, mChallengeRate);
        values.put(ChallengeContract.ChallengeEntry.COLUMN_TYPE, mChallengeType);
        return null;
    }
}
