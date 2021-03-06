package com.kgb.k_app.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kgb.k_app.R;
import com.kgb.k_app.data.Data;
import com.kgb.k_app.data.DataSource;

public class ChooseChallengeAdapter<Model extends Data> extends BaseAdapter {
    private class ViewHolder {
        TextView textItem;
    }
    private Activity mActivity;
    private DataSource<Model> mDataSource;

    public ChooseChallengeAdapter(Activity activity, DataSource<Model> dataSource) {
        mActivity = activity;
        mDataSource = dataSource;
    }

    @Override
    public int getCount() {
        return mDataSource.count();
    }

    @Override
    public Model getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mDataSource.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            convertView = LayoutInflater.from(mActivity).inflate(R.layout.choose_challenge_item, parent, false);
            holder = new ViewHolder();
            holder.textItem = (TextView) convertView.findViewById(R.id.text_item);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        holder.textItem.setText(getItem(position).getName());
        return convertView;
    }
}
