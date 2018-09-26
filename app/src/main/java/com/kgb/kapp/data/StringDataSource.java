package com.kgb.kapp.data;

import android.app.Activity;

import com.kgb.kapp.R;
import com.kgb.kapp.model.StringData;

import java.util.ArrayList;
import java.util.List;

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
