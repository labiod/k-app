package com.kgb.kapp.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.kgb.kapp.database.ChallengeContract;
import com.kgb.kapp.database.DBConnection;
import com.kgb.kapp.model.YourChallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/24/17
 */

public class YourChallengeDataSource extends BaseDataSource<YourChallenge>  {

    private List<YourChallenge> mUnConfirmed = new ArrayList<>();

    public YourChallengeDataSource(DBConnection dbConnection) {
        super(dbConnection);
    }

    @Override
    public void confirmedChanges(YourChallenge yourChallenge) {
        if (yourChallenge.getId() == -1) {
            yourChallenge.setId(getDBConnection().insert(yourChallenge));
            mUnConfirmed.remove(yourChallenge);
        } else {
            getDBConnection().update(yourChallenge);
        }
    }

    @Override
    protected List<YourChallenge> retrieveFromDB(String where) {
        SQLiteDatabase database = getDBConnection().connect(DBConnection.READ_MODE);
        String sql = createQuery(where);
        Cursor cursor = database.rawQuery(sql, null);
        List<YourChallenge> result = new ArrayList<>();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                result.add(new YourChallenge(cursor));
            } while (cursor.moveToNext());
            cursor.close();
        }
        result.addAll(mUnConfirmed);
        return result;
    }

    private String createQuery(String where) {
        String tableName = ChallengeContract.YourChallengeEntry.TABLE_NAME;
        String[] columns = getColumns();
        String joinTable = ChallengeContract.ChallengeEntry.TABLE_NAME;
        String fid = ChallengeContract.YourChallengeEntry.COLUMN_CHALLENGE_ID;
        String jid = ChallengeContract.ChallengeEntry._ID;
        String sql = "SELECT %s FROM %s INNER JOIN %s ON %s.%s=%s.%s";
        if (where != null) {
            sql += " WHERE " + where;
        }
        return String.format(sql, join(columns, ", "), tableName, joinTable, tableName, fid, joinTable, jid);
    }

    private String join(String[] columns, String separator) {
        String result = "";
        boolean first = true;
        for (String column : columns) {
            if (first) {
                first = false;
            } else {
                result += separator;
            }
            result += column;
        }
        return result;
    }

    @Override
    public int count() {
        retrieveData();
        return mDataList.size();
    }

    @Override
    public YourChallenge get(int position) {
        retrieveData();
        return mDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDataList.get(position).getId();
    }

    public static String[] getColumns() {
        return new String[] {
                ChallengeContract.YourChallengeEntry.TABLE_NAME + "." + ChallengeContract.YourChallengeEntry._ID,
                ChallengeContract.YourChallengeEntry.COLUMN_START_DATE,
                ChallengeContract.YourChallengeEntry.COLUMN_EXPIRE_DATE,
                ChallengeContract.YourChallengeEntry.COLUMN_CHALLENGE_ID,
                //Columns for Challenge
                ChallengeContract.ChallengeEntry.COLUMN_NAME,
                ChallengeContract.ChallengeEntry.COLUMN_DESCRIPTION,
                ChallengeContract.ChallengeEntry.COLUMN_RATE,
                ChallengeContract.ChallengeEntry.COLUMN_TYPE
        };
    }

    public void addChallenge(YourChallenge yourChallenge) {
        mUnConfirmed.add(yourChallenge);
        mDataList.add(yourChallenge);
    }

    public void clearChooseChallenges() {
        retrieveData();
        for (YourChallenge challenge : mDataList) {
            getDBConnection().delete(challenge);
        }
    }
}
