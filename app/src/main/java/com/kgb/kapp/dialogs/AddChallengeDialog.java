package com.kgb.kapp.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kgb.kapp.R;
import com.kgb.kapp.database.ChallengeDBHelper;
import com.kgb.kapp.database.DBConnection;
import com.kgb.kapp.model.Challenge;

/**
 * @author Krzysztof Betlej <kgbetlej@gmail.com>.
 * @date 2/23/17
 * @copyright Copyright (c) 2017 KGBetlej
 */

public class AddChallengeDialog extends DialogFragment {


    public static final String TAG = AddChallengeDialog.class.getSimpleName();

    public interface AddChallengeListener {
        void onItemAdded(Challenge newItem);
        void onCancel();
    }

    private AddChallengeDialog.AddChallengeListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.add_challenge);
        final View layout = View.inflate(getActivity(), R.layout.add_challenge_layout, null);
        builder.setView(layout);
        builder.setPositiveButton(R.string.button_add_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView challengeName = layout.findViewById(R.id.challenge_name);
                TextView challengeType = layout.findViewById(R.id.challenge_type);
                TextView challengeRate = layout.findViewById(R.id.challenge_rate);
                Challenge challenge = new Challenge(
                    challengeName.getText().toString(),
                    Integer.parseInt(challengeRate.getText().toString()),
                    Integer.parseInt(challengeType.getText().toString())
                    );
                DBConnection dbConnection = new DBConnection(new ChallengeDBHelper(getActivity(),
                new DatabaseErrorHandler() {
                    @Override
                    public void onCorruption(SQLiteDatabase dbObj) {
                        Log.e(TAG, "onCorruption: ");
                    }
                }));
                SQLiteDatabase db = dbConnection.connect(DBConnection.WRITE_MODE);
                if (db != null) {
                    db.insert(challenge.getTableName(), null, challenge.getData());
                    db.close();
                    if(mListener != null) {
                        mListener.onItemAdded(challenge);
                    }
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

    public void setListener(AddChallengeListener listener) {
        mListener = listener;
    }
}
