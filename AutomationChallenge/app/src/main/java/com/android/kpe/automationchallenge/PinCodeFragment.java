package com.android.kpe.automationchallenge;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
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
        getBtnRestart();
        addEventHandlers();
    }

    private void addEventHandlers() {
        getActivity().findViewById(R.id.button1).setOnClickListener(this);
        getActivity().findViewById(R.id.button2).setOnClickListener(this);
        getActivity().findViewById(R.id.button3).setOnClickListener(this);
        getActivity().findViewById(R.id.button4).setOnClickListener(this);
        getActivity().findViewById(R.id.button5).setOnClickListener(this);
        getActivity().findViewById(R.id.button6).setOnClickListener(this);
        getActivity().findViewById(R.id.button7).setOnClickListener(this);
        getActivity().findViewById(R.id.button8).setOnClickListener(this);
        getActivity().findViewById(R.id.button9).setOnClickListener(this);
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
            mBtnRestart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mActualPin = "";
                    mExpectedPin = "";
                    setActualPin();
                    setExpectedPin();
                }
            });
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
            mTxtExpectedPin = (TextView) getView().findViewById(R.id.txtPin);
        }
        return mTxtExpectedPin;
    }

    private void setActualPin() {
        String actual = (mActualPin == null) ? "" : mActualPin;

        String text = null;
        if(actual.length() == 0) {
            text = "Click to enter the pin";
        } else if(actual.equals(mExpectedPin)) {
            text = "Success";
        } else if(mExpectedPin.startsWith(actual)){
            text = getResources().getString(R.string.pin_entered) + " " + actual;
        } else {
            text = "Fail";
        }
        getTxtActualPin().setText(text);

    }

    private void setExpectedPin() {
        if(mExpectedPin == null || mExpectedPin.isEmpty()) {
            mExpectedPin = String.valueOf(getRandomInt(101, 99999)).replace("0", "");
        }
        getTxtExpectedPin().setText(getResources().getString(R.string.enter_pin) + " " + String.valueOf(mExpectedPin));
    }

    private int getRandomInt(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
            case R.id.button2:
            case R.id.button3:
            case R.id.button4:
            case R.id.button5:
            case R.id.button6:
            case R.id.button7:
            case R.id.button8:
            case R.id.button9:
                String text = ((Button) getView().findViewById(v.getId())).getText().toString();
                pinCodeClicked(text);
                break;
            case R.id.btnRestart:
                break;
            default:
                break;
        }
    }

    private void pinCodeClicked(String value) {
        if(mActualPin == null) {
            mActualPin = value;
        } else {
            mActualPin += value;
        }
        setActualPin();
    }
}
