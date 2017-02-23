package com.kgb.k_app.data;

import com.kgb.k_app.database.DBConnection;
import com.kgb.k_app.model.Challenge;

import java.util.List;

/**
 * @author Krzysztof Betlej <k.betlej@samsung.com>.
 * @date 2/21/17
 * @copyright Copyright (c) 2016 by Samsung Electronics Polska Sp. z o. o.
 */

public class ChallengeDataSource implements DataSource<Challenge> {

    private DBConnection mDbConnection;

    public ChallengeDataSource(DBConnection dbConnection) {
        mDbConnection = dbConnection;
    }

    @Override
    public void saveChanges(Challenge challenge) {
        if (challenge.getId() == -1) {
            mDbConnection.insert(challenge);
        } else{
            mDbConnection.update(challenge);
        }
    }

    @Override
    public List<Challenge> retrieveData() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public Challenge get(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
