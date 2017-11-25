package com.android.kpe.automationchallenge;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by LocalUser on 23/11/2017.
 */

public class AlertDialogChallengeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_alert_dialog, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FragmentManager fm = getFragmentManager();
        AlertDialogFragment alertDialog = AlertDialogFragment.newInstance(this, "Is the answer correct?");
        alertDialog.show(fm, "fragment_alert");
    }

    public void setSuccessMessage(Boolean success) {
        TextView txtStatus = (TextView) getView().findViewById(R.id.txtActual);
        if(success) {
            txtStatus.setText(getString(R.string.success));
        } else {
            txtStatus.setText(getString(R.string.fail));
        }
    }

}
