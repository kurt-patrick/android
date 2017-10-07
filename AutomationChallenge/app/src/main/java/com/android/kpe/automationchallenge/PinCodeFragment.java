package com.android.kpe.automationchallenge;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
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
    private TextView mEnterPin = null;

    public PinCodeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getEnterPin().setText(getExpectedPin());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pin_code, container, false);
    }

    private Button getRestart() {
        if(mBtnRestart == null) {
            mBtnRestart = (Button) getActivity().findViewById(R.id.btnRestart);
        }
        return mBtnRestart;
    }

    private TextView getEnterPin() {
        if(mEnterPin == null) {
            mEnterPin = (TextView) getActivity().findViewById(R.id.txtPin);
        }
        return mEnterPin;
    }

    private String getExpectedPin() {
        if(mExpectedPin == null || mExpectedPin.isEmpty()) {
            Random random = new Random(1000);
            int pin = random.nextInt();
            mExpectedPin = R.string.enter_pin + " " + String.valueOf(pin);
        }
        return mExpectedPin;
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
