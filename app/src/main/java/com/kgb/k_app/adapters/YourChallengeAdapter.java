package com.kgb.k_app.adapters;

import android.app.DatePickerDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kgb.k_app.R;
import com.kgb.k_app.data.YourChallengeDataSource;
import com.kgb.k_app.database.DBConnection;
import com.kgb.k_app.model.Challenge;
import com.kgb.k_app.model.YourChallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/24/17
 * @copyright Copyright (c)2017 KGBetlej
 */

public class YourChallengeAdapter extends BaseAdapter {

    public static final int UNCONFIRMED_CHALLENGE = 0;
    public static final int CONFIRMED_CHALLENGE = 1;

    private int mYear;
    private int mMonth;
    private int mDay;
    private TextView mResult;

    private class Holder {
        TextView challengeName;
        TextView challengeStartDate;
        TextView challengeExpireDate;
        Button confirmButton;

    }
    private YourChallengeDataSource mYourChallenges;
    private DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (mResult == null) {

            }
            // set selected date into textview
            mResult.setText(new StringBuilder().append(monthOfYear + 1)
                    .append("-").append(dayOfMonth).append("-").append(year)
                    .append(" "));

        }
    };

    public YourChallengeAdapter(DBConnection connection) {
        mYourChallenges = new YourChallengeDataSource(connection);
        mYourChallenges.retrieveData();
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public int getCount() {
        return mYourChallenges.count();
    }

    @Override
    public YourChallenge getItem(int position) {
        return mYourChallenges.get(position);
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getId() == -1 ? UNCONFIRMED_CHALLENGE : CONFIRMED_CHALLENGE;
    }

    @Override
    public long getItemId(int position) {
        return mYourChallenges.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Holder holder;
        int type = getItemViewType(position);
        final YourChallenge challenge = getItem(position);
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = getLayout(inflater, type, parent);
            holder = new Holder();
            holder.challengeName = (TextView) convertView.findViewById(R.id.challenge_name);
            holder.challengeStartDate = (TextView) convertView.findViewById(R.id.challenge_start_date);
            holder.challengeExpireDate = (TextView) convertView.findViewById(R.id.challenge_expire_date);
            if (type == UNCONFIRMED_CHALLENGE) {
                holder.confirmButton = (Button) convertView.findViewById(R.id.challenge_confirm);
            }
            holder.confirmButton = (Button) convertView.findViewById(R.id.challenge_confirm);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.challengeName.setText(challenge.getName());
        if (type == UNCONFIRMED_CHALLENGE) {
            setCurrentDate(holder);
            holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mYourChallenges.confirmedChanges(challenge);
                    notifyDataSetChanged();
                }
            });
            holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mYourChallenges.confirmedChanges(challenge);
                    notifyDataSetChanged();
                }
            });
            prepareDatePickers(holder, challenge);
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
            holder.challengeStartDate.setText(format.format(challenge.getStartDate()));
            holder.challengeExpireDate.setText(format.format(challenge.getExpireDate()));
        }
        return convertView;
    }


    public YourChallengeDataSource getDataSource() {
        return mYourChallenges;
    }

    private void prepareDatePickers(Holder holder, final YourChallenge challenge) {
        holder.challengeStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker((TextView) v);
            }
        });
        holder.challengeStartDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
                try {
                    challenge.setStartDate(format.parse(s.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        holder.challengeExpireDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker((TextView) v);
            }
        });
        holder.challengeStartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker((TextView) v);
            }
        });
        holder.challengeExpireDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
                try {
                    challenge.setExpireDate(format.parse(s.toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setCurrentDate(Holder holder) {
        holder.challengeStartDate.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(mDay).append("-")
                .append(mMonth + 1).append("-")
                .append(mYear).append(" "));
        holder.challengeExpireDate.setText("");
    }

    private void showDatePicker(TextView textView) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(textView.getContext(),
                mDatePickerListener, mYear, mMonth, mDay);
        mResult = textView;
        datePickerDialog.show();
    }

    private View getLayout(LayoutInflater inflater, int type, ViewGroup parent) {
        return type == UNCONFIRMED_CHALLENGE ?
                inflater.inflate(R.layout.your_challenge_confirm_layout, parent, false) :
                inflater.inflate(R.layout.your_challenge_layout, parent, false);
    }

    public void addChallenge(Challenge challenge) {
        mYourChallenges.addChallenge(new YourChallenge(challenge));
        notifyDataSetChanged();
    }
}
