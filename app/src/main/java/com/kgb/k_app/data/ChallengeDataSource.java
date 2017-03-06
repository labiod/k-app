package com.kgb.k_app.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kgb.k_app.database.ChallengeContract;
import com.kgb.k_app.database.DBConnection;
import com.kgb.k_app.model.Challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/21/17
 * @copyright
 */

public class ChallengeDataSource implements DataSource<Challenge>{

    private DBConnection mDbConnection;
    private List<Challenge> mResult;

    public ChallengeDataSource(DBConnection dbConnection) {
        mDbConnection = dbConnection;
    }

    @Override
    public void confirmedChanges(Challenge challenge) {
        if (challenge.getId() == -1) {
            mDbConnection.insert(challenge);
        } else{
            mDbConnection.update(challenge);
        }
    }

    @Override
    public List<Challenge> retrieveData() {
        if (mResult == null) {
            SQLiteDatabase database = mDbConnection.connect(DBConnection.READ_MODE);
            Cursor cursor = database.query(ChallengeContract.ChallengeEntry.TABLE_NAME, Challenge.getColumns(), null, null, null, null, null);
            mResult = new ArrayList<>();
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    mResult.add(new Challenge(cursor));
                } while (cursor.moveToNext());
                cursor.close();
            }
        }
        return mResult;
    }

    @Override
    public List<Challenge> retrieveData(String where) {
        SQLiteDatabase database = mDbConnection.connect(DBConnection.READ_MODE);
        Cursor cursor = database.query(ChallengeContract.ChallengeEntry.TABLE_NAME, Challenge.getColumns(), where, null, null, null, null);
        mResult = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                mResult.add(new Challenge(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return mResult;
    }

    @Override
    public int count() {
        retrieveData();
        return mResult.size();
    }

    @Override
    public Challenge get(int position) {
        retrieveData();
        return mResult.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mResult.get(position).getId();
    }
}
