package com.kgb.k_app.data;

import android.app.Activity;

import com.kgb.k_app.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jan on 27.11.2016.
 */

public class StringDataSource implements DataSource<String> {
    List<String> mData = new ArrayList<>();
    public StringDataSource(Activity activity) {
        String[] source = activity.getResources().getStringArray(R.array.challenges_options);
        mData.addAll(Arrays.asList(source));
    }

    @Override
    public void saveChanges(String s) {

    }

    @Override
    public List<String> retrieveData() {
        return mData;
    }

    @Override
    public int count() {
        return mData.size();
    }

    @Override
    public String get(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
