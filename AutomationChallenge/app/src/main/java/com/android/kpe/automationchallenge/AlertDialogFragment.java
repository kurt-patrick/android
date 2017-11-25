package com.android.kpe.automationchallenge;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.DialogFragment;
import android.app.AlertDialog;
import android.widget.TextView;

/**
 * Created by LocalUser on 23/11/2017.
 */

public class AlertDialogFragment extends DialogFragment {

    private static final String TITLE = "title";

    private String mMathEquation = null;
    private Boolean mMathEquationIsCorrect = false;
    private AlertDialogChallengeFragment mParent = null;

    public AlertDialogFragment() {
    }

    public static AlertDialogFragment newInstance(AlertDialogChallengeFragment parent, String title) {
        AlertDialogFragment frag = new AlertDialogFragment();
        frag.mParent = parent;
        Bundle args = new Bundle();
        args.putString(TITLE, title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString(TITLE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(GenerateMathEquation());
        alertDialogBuilder.setPositiveButton("YES",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                mParent.setSuccessMessage(mMathEquationIsCorrect);
            }
        });
        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (dialog != null) {
                    dialog.dismiss();
                }
                mParent.setSuccessMessage(mMathEquationIsCorrect == false);
            }

        });

        return alertDialogBuilder.create();
    }

    String GenerateMathEquation() {
        int answer = 0;
        int lhs = RandomHelper.between(1, 49);
        int rhs = RandomHelper.between(50, 98);
        int symbol = RandomHelper.between(1, 3);
        mMathEquationIsCorrect = RandomHelper.getRandomBoolean();

        switch (symbol) {
            case 1:
                answer = lhs + rhs;
                answer += (mMathEquationIsCorrect) ? 0 : (RandomHelper.getRandomBoolean()) ? -1 : 1;
                mMathEquation = String.format("%1$s + %2$s == %3$s", lhs, rhs, answer);
                break;
            case 2:
                answer = rhs - lhs;
                answer += (mMathEquationIsCorrect) ? 0 : (RandomHelper.getRandomBoolean()) ? -1 : 1;
                mMathEquation = String.format("%1$s - %2$s == %3$s", rhs, lhs, answer);
                break;
            default:
                answer = lhs * rhs;
                answer += (mMathEquationIsCorrect) ? 0 : (RandomHelper.getRandomBoolean()) ? -1 : 1;
                mMathEquation = String.format("%1$s * %2$s == %3$s", lhs, rhs, answer);
        }

        return mMathEquation;
    }


}
