package com.kgb.k_app.data;

import android.app.Activity;

import com.kgb.k_app.R;
import com.kgb.k_app.model.StringData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 27.11.2016.
 */

public class StringDataSource implements DataSource<StringData> {
    List<StringData> mData = new ArrayList<>();
    public StringDataSource(Activity activity) {
        String[] source = activity.getResources().getStringArray(R.array.challenges_options);
        mData.addAll(StringData.asList(source));
    }

    @Override
    public void confirmedChanges(StringData s) {

    }

    @Override
    public List<StringData> retrieveData() {
        return mData;
    }

    @Override
    public List<StringData> retrieveData(String where) {
        return mData;
    }

    @Override
    public int count() {
        return mData.size();
    }

    @Override
    public StringData get(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
