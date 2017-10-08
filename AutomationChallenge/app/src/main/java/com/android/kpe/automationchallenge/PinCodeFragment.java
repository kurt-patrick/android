package com.android.kpe.automationchallenge;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class PinCodeFragment extends Fragment implements View.OnClickListener {

    private String mActualPin = null;
    private Button mBtnRestart = null;
    private String mExpectedPin = null;
    private TextView mTxtActualPin = null;
    private TextView mTxtExpectedPin = null;

    public PinCodeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setExpectedPin();
        setActualPin();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_code, container, false);
    }

    private Button getBtnRestart() {
        if(mBtnRestart == null) {
            mBtnRestart = (Button) getActivity().findViewById(R.id.btnRestart);
        }
        return mBtnRestart;
    }

    private TextView getTxtActualPin() {
        if(mTxtActualPin == null) {
            mTxtActualPin = (TextView) getView().findViewById(R.id.txtPinEntered);
        }
        return mTxtActualPin;
    }

    private TextView getTxtExpectedPin() {
        if(mTxtExpectedPin == null) {
            View view = getView();
            mTxtExpectedPin = (TextView) getView().findViewById(R.id.txtPin);
        }
        return mTxtExpectedPin;
    }

    private void setActualPin() {
        String actual = (mActualPin == null) ? "" : mActualPin;
        getTxtActualPin().setText(getResources().getString(R.string.pin_entered) + " " + actual);
    }

    private void setExpectedPin() {

        if(mExpectedPin == null || mExpectedPin.isEmpty()) {
            Random random = new Random(1000);
            int pin = random.nextInt();
            mExpectedPin = String.valueOf(pin);
        }

        getTxtExpectedPin().setText(getResources().getString(R.string.enter_pin) + " " + String.valueOf(mExpectedPin));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnRestart:
                break;
            default:
                break;
        }

    }
}
