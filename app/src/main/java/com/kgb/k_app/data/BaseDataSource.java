package com.kgb.k_app.data;

import com.kgb.k_app.database.DBConnection;

import java.util.List;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 3/6/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public abstract class BaseDataSource<Model extends Data> implements DataSource<Model>, DBConnection.OnDBChanged {
    private final DBConnection mDbConnection;
    protected List<Model> mDataList;
    private boolean mDataChanged = false;

    public BaseDataSource(DBConnection dbConnection) {
        mDbConnection = dbConnection;
        mDbConnection.addOnDBChanged(this);
    }

    @Override
    public void confirmedChanges(Model model) {

    }

    @Override
    public final List<Model> retrieveData() {
        if (mDataList == null || mDataChanged) {
            mDataChanged = false;
            mDataList = retrieveFromDB(null);
        }
        return mDataList;
    }

    @Override
    public final List<Model> retrieveData(String where) {
        mDataList = retrieveFromDB(where);
        return mDataList;
    }

    @Override
    public void onChange(Data record, int action) {
        mDataChanged = true;
    }

    protected DBConnection getDBConnection() {
        return mDbConnection;
    }

    protected abstract List<Model> retrieveFromDB(String where);
}
