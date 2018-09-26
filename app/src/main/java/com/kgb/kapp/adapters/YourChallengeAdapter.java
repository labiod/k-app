package com.kgb.kapp.adapters;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.kgb.kapp.R;
import com.kgb.kapp.data.YourChallengeDataSource;
import com.kgb.kapp.database.DBConnection;
import com.kgb.kapp.helper.ResHelper;
import com.kgb.kapp.model.Challenge;
import com.kgb.kapp.model.YourChallenge;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/24/17
 * @copyright Copyright (c)2017 BitAge
 */

public class YourChallengeAdapter extends RecyclerView.Adapter<YourChallengeAdapter.Holder> {
    class Holder extends RecyclerView.ViewHolder {
        TextView challengeName;
        TextView challengeStartDate;
        TextView challengeExpireDate;
        TextView challengeProgress;
        Button confirmButton;
        ViewGroup bottomPanel;
        int disabledColor;
        int enabledColor;

        public Holder(View rootView) {
            super(rootView);
            challengeName = rootView.findViewById(R.id.challenge_name);
            challengeStartDate = rootView.findViewById(R.id.challenge_start_date);
            challengeExpireDate = rootView.findViewById(R.id.challenge_expire_date);
            challengeProgress = rootView.findViewById(R.id.challenge_progress);
            bottomPanel = rootView.findViewById(R.id.your_challenge_bottom_panel);
            enabledColor = ResHelper.getColor(rootView.getContext(), R.color.colorEnabledConfirmBackground);
            disabledColor = ResHelper.getColor(rootView.getContext(), R.color.colorDisabledConfirmBackground);
            confirmButton = rootView.findViewById(R.id.challenge_confirm);
        }
    }

    public static final int UNCONFIRMED_CHALLENGE = 0;
    public static final int CONFIRMED_CHALLENGE = 1;

    private int mYear;
    private int mMonth;
    private int mDay;
    private TextView mResult;

    private YourChallengeDataSource mYourChallenges;
    private DatePickerDialog.OnDateSetListener mDatePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            if (mResult != null) {
                // set selected date into textview
                mResult.setText(new StringBuilder()
                        .append(dayOfMonth).append("-")
                        .append(monthOfYear + 1).append("-")
                        .append(year).append(" "));
            }
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

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holder(getLayout(inflater, viewType, parent));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        final YourChallenge challenge = getItem(position);
        Context context = holder.itemView.getContext();
        holder.challengeName.setText(challenge.getName());
        if (getItemViewType(position) == UNCONFIRMED_CHALLENGE) {
            holder.confirmButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mYourChallenges.confirmedChanges(challenge);
                    notifyDataSetChanged();
                }
            });
            prepareDatePickers(holder, challenge);
            setCurrentDate(holder);
            holder.confirmButton.setEnabled(canConfirm(challenge));
        } else {
            SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH);
            holder.challengeStartDate.setText(format.format(challenge.getStartDate()));
            holder.challengeExpireDate.setText(format.format(challenge.getExpireDate()));
            holder.challengeProgress.setText(context.getString(R.string.challenge_progress_format, challenge.getProgress(), challenge.getMaxProgress()));
        }
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
    public int getItemCount() {
        return mYourChallenges.count();
    }

    private boolean canConfirm(YourChallenge challenge) {
        return challenge.getStartDate() != null && challenge.getExpireDate() != null;
    }


    public YourChallengeDataSource getDataSource() {
        return mYourChallenges;
    }

    private void prepareDatePickers(final Holder holder, final YourChallenge challenge) {
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

                    boolean enabled = canConfirm(challenge);
                    holder.confirmButton.setEnabled(enabled);
                    int color = enabled ? holder.enabledColor : holder.disabledColor;
                    holder.bottomPanel.findViewById(R.id.your_challenge_confirm_left_bg).setBackground(new ColorDrawable(color));
                    holder.bottomPanel.findViewById(R.id.your_challenge_confirm_right_bg).setBackground(new ColorDrawable(color));
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

                    boolean enabled = canConfirm(challenge);
                    holder.confirmButton.setEnabled(enabled);
                    int color = enabled ? holder.enabledColor : holder.disabledColor;
                    holder.bottomPanel.findViewById(R.id.your_challenge_confirm_left_bg).setBackground(new ColorDrawable(color));
                    holder.bottomPanel.findViewById(R.id.your_challenge_confirm_right_bg).setBackground(new ColorDrawable(color));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private YourChallenge getItem(int position) {
        return mYourChallenges.get(position);
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
