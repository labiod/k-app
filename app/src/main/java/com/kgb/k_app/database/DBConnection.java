package com.kgb.k_app.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.kgb.k_app.data.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/21/17
 * @copyright
 */

public class DBConnection {
    public interface OnDBChanged {
        public static final int INSERT = 1;
        public static final int UPDATE = 2;
        public static final int DELETE = 4;
        void onChange(Data record, int action);
    }
    public static final int READ_MODE = 1;
    public static final int WRITE_MODE = 2;
    private SQLiteOpenHelper mDBHelper;
    private List<OnDBChanged> mListeners = new ArrayList<>();
    public DBConnection(SQLiteOpenHelper dbHelper) {
        mDBHelper = dbHelper;
    }

    public SQLiteDatabase connect(int mode) {
        switch (mode) {
            case READ_MODE:
                return mDBHelper.getReadableDatabase();
            case WRITE_MODE:
                return mDBHelper.getWritableDatabase();
            default:
                return null;
        }
    }

    public void update(Data data) {
        SQLiteDatabase db = connect(WRITE_MODE);
        int result = db.update(data.getTableName(), data.getData(), String.format(Locale.ENGLISH, "%s = %d", Data.DataEntry._ID, data.getId()), null);
        if (result != -1) {
            notifyDBChanges(data, OnDBChanged.UPDATE);
        }
        db.close();
    }

    public long insert(Data data) {
        SQLiteDatabase db = connect(WRITE_MODE);
        long result = db.insert(data.getTableName(), null, data.getData());
        if (result != -1) {
            notifyDBChanges(data, OnDBChanged.INSERT);
        }
        db.close();
        return result;
    }

    public int delete(Data data) {
        SQLiteDatabase db = connect(WRITE_MODE);
        String where = data.getIdColumnName() + " = " + data.getId();
        int result = db.delete(data.getTableName(), where, null);
        if (result != 0) {
            notifyDBChanges(data, OnDBChanged.DELETE);
        }
        db.close();
        return result;
    }

    private void notifyDBChanges(Data data, int action) {
        for (OnDBChanged listener : mListeners) {
            listener.onChange(data, action);
        }
    }

    public void addOnDBChanged(OnDBChanged listener) {
        mListeners.add(listener);
    }
}
