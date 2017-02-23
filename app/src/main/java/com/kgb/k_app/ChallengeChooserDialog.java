package com.kgb.k_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.kgb.k_app.adapters.ChooseChallengeAdapter;
import com.kgb.k_app.data.Data;
import com.kgb.k_app.data.DataSource;
import com.kgb.k_app.data.StringDataSource;
import com.kgb.k_app.model.StringData;

public class ChallengeChooserDialog extends DialogFragment {
    interface ChallengeChooserListener {
        void onItemChoose(DataSource<? extends Data> source, int itemChoose);
        void onCancel();
    }

    private ChallengeChooserListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final StringDataSource dataSource = new StringDataSource(getActivity());
        builder.setTitle(R.string.choose_challeng_dialog_title);
        builder.setAdapter(new ChooseChallengeAdapter<>(getActivity(), dataSource), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null) {
                    mListener.onItemChoose(dataSource, which);
                }
            }
        });
        builder.setNegativeButton(R.string.button_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(mListener != null) {
                    mListener.onCancel();
                }
            }
        });
        return builder.create();
    }

    public void setListener(ChallengeChooserListener listener) {
        mListener = listener;
    }
}
