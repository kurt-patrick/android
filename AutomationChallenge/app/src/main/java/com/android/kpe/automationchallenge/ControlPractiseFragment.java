package com.android.kpe.automationchallenge;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.KeyCharacterMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ControlPractiseFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Button mBtnToast = null;

    // ToggleButton
    ToggleButton mToggleButton = null;
    TextView mToggleTextView = null;
    Boolean mToggleExpectedState = false;

    // CheckBox
    CheckBox mCheckBox = null;
    TextView mCheckBoxTextView = null;
    Boolean mCheckBoxExpectedState = false;

    // Switch
    Switch mSwitch = null;
    TextView mSwitchTextView = null;
    Boolean mSwitchExpectedState = false;

    // Spinner/DropDownList
    Spinner mSpinner = null;
    TextView mSpinnerTextView = null;
    String mSpinnerExpectedState = null;

    // RadioGroup
    TextView mRadioGroupTextView = null;
    String mRadioGroupExpectedState = null;

    public ControlPractiseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_control_practise, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateSpinner();
        getBtnToast();
        setToggleExpectedState();
        setCheckBoxExpectedState();
        setSwitchExpectedState();
        setSpinnerExpectedState();
        setRadioGroupExpectedState();
        setSuccessStateForAllTextViews();
    }

    private void setSuccessStateForAllTextViews() {
        setTextViewSuccessColor(getToggleTextView(), isToggleStateCorrrect());
        setTextViewSuccessColor(getCheckBoxTextView(), isCheckStateCorrrect());
        setTextViewSuccessColor(getSwitchTextView(), isSwitchStateCorrrect());
        setTextViewSuccessColor(getSpinnerTextView(), isSpinnerStateCorrrect());
        setTextViewSuccessColor(getRadioGroupTextView(), isRadioGroupStateCorrrect());
    }

    private Boolean getSuccessStateForAlltextViews() {
        return isToggleStateCorrrect() &&
                isCheckStateCorrrect() &&
                isSwitchStateCorrrect() &&
                isSpinnerStateCorrrect() &&
                isRadioGroupStateCorrrect();
    }

    private void setToggleExpectedState() {
        mToggleExpectedState = RandomHelper.getRandomBoolean();

        getToggleTextView().setText(mToggleExpectedState ? "Toggle (On)" : "Toggle (Off)");

        // add the event handler for the toggle button
        getToggleButton();
    }

    private void setCheckBoxExpectedState() {
        mCheckBoxExpectedState = RandomHelper.getRandomBoolean();

        getCheckBoxTextView().setText(mCheckBoxExpectedState ? "Check (On)" : "Check (Off)");

        // add the event handler for the toggle button
        getCheckBox();
    }

    private void setSwitchExpectedState() {
        mSwitchExpectedState = RandomHelper.getRandomBoolean();

        getSwitchTextView().setText(mSwitchExpectedState ? "Switch (On)" : "Switch (Off)");

        // add the event handler for the toggle button
        getSwitch();
    }

    private void populateSpinner() {
        Spinner spinner = getSpinner();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.spinner_values));
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }

    private TextView getToggleTextView() {
        if(mToggleTextView == null) {
            mToggleTextView = ((TextView) getView().findViewById(R.id.txtToggle));
        }
        return mToggleTextView;
    }

    private ToggleButton getToggleButton() {
        if(mToggleButton == null) {
            mToggleButton = ((ToggleButton) getView().findViewById(R.id.toggleButton2));
            mToggleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextViewSuccessColor(getToggleTextView(), isToggleStateCorrrect());
                }
            });
        }
        return mToggleButton;
    }

    private Boolean isToggleStateCorrrect() {
        ToggleButton toggleButton = getToggleButton();
        return toggleButton.isChecked() == mToggleExpectedState;
    }


    private TextView getCheckBoxTextView() {
        if(mCheckBoxTextView == null) {
            mCheckBoxTextView = ((TextView) getView().findViewById(R.id.txtCheck));
        }
        return mCheckBoxTextView;
    }

    private CheckBox getCheckBox() {
        if(mCheckBox == null) {
            mCheckBox = ((CheckBox) getView().findViewById(R.id.checkBox2));
            mCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextViewSuccessColor(getCheckBoxTextView(), isCheckStateCorrrect());
                }
            });
        }
        return mCheckBox;
    }

    private Boolean isCheckStateCorrrect() {
        CheckBox control = getCheckBox();
        return control.isChecked() == mCheckBoxExpectedState;
    }

    // Switch
    private TextView getSwitchTextView() {
        if(mSwitchTextView == null) {
            mSwitchTextView = ((TextView) getView().findViewById(R.id.txtSwitch));
        }
        return mSwitchTextView;
    }

    private Switch getSwitch() {
        if(mSwitch == null) {
            mSwitch = ((Switch) getView().findViewById(R.id.switch2));
            mSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setTextViewSuccessColor(getSwitchTextView(), isSwitchStateCorrrect());
                }
            });
        }
        return mSwitch;
    }

    private Boolean isSwitchStateCorrrect() {
        Switch toggleButton = getSwitch();
        return toggleButton.isChecked() == mSwitchExpectedState;
    }

    // Spinner (aka) DropDownList
    private TextView getSpinnerTextView() {
        if(mSpinnerTextView == null) {
            mSpinnerTextView = ((TextView) getView().findViewById(R.id.txtSpinner));
        }
        return mSpinnerTextView;
    }

    private Boolean isSpinnerStateCorrrect() {
        Spinner spinner = getSpinner();
        Object selectedItem = spinner.getSelectedItem();
        return selectedItem != null && mSpinnerExpectedState != null && selectedItem.equals(mSpinnerExpectedState);
    }

    private Spinner getSpinner() {
        if(mSpinner == null) {
            mSpinner = ((Spinner) getView().findViewById(R.id.spinner));
            mSpinner.setOnItemSelectedListener(this);
        }
        return mSpinner;
    }

    private void setSpinnerExpectedState() {
        mSpinnerExpectedState = RandomHelper.getRandomString("Six", "Seven", "Eight", "Nine", "Ten");

        getSpinnerTextView().setText("Select (" + mSpinnerExpectedState + ")");

        // add the event handler for the toggle button
        Spinner spinner = getSpinner();
    }
    // [End] Spinner (aka) DropDownList


    // [Start] RadioGroup
    private TextView getRadioGroupTextView() {
        if(mRadioGroupTextView == null) {
            mRadioGroupTextView = ((TextView) getView().findViewById(R.id.txtRadioGroup));
        }
        return mRadioGroupTextView;
    }

    private Boolean isRadioGroupStateCorrrect() {
        if(mRadioGroupExpectedState == null) {
            throw new NullPointerException();
        }

        switch (mRadioGroupExpectedState) {
            case "One":
                return getRadioButton(R.id.radioOne).isChecked();
            case "Two":
                return getRadioButton(R.id.radioTwo).isChecked();
            case "Three":
                return getRadioButton(R.id.radioThree).isChecked();
            default:
                throw new KeyCharacterMap.UnavailableException("case not implemented: " + mRadioGroupExpectedState);
        }

    }

    private RadioButton getRadioButton(int id) {
        return ((RadioButton) getView().findViewById(id));
    }

    private void setRadioGroupExpectedState() {
        mRadioGroupExpectedState = RandomHelper.getRandomString("One", "Two", "Three");

        getRadioGroupTextView().setText("Select (" + mRadioGroupExpectedState + ")");

        // add the event handlers
        addRadioButtonOnClickListener(R.id.radioOne);
        addRadioButtonOnClickListener(R.id.radioTwo);
        addRadioButtonOnClickListener(R.id.radioThree);

    }

    private void addRadioButtonOnClickListener(int id) {
        getRadioButton(id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTextViewSuccessColor(getRadioGroupTextView(), isRadioGroupStateCorrrect());
            }
        });
    }

    // [End] RadioGroup

    private Boolean setTextViewSuccessColor(TextView textView, Boolean isSuccess) {
        if(isSuccess) {
            textView.setBackgroundColor(Color.GREEN);
        } else {
            textView.setBackgroundColor(Color.RED);
        }
        return isSuccess;
    }

    private Button getBtnToast() {
        if(mBtnToast == null) {
            mBtnToast = ((Button) getView().findViewById(R.id.btnToast));
            mBtnToast.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message = getSuccessStateForAlltextViews() ? getString(R.string.success) : getString(R.string.fail);
                    Toast toast = Toast.makeText(getView().getContext(), message, Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }
        return mBtnToast;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setTextViewSuccessColor(getSpinnerTextView(), isSpinnerStateCorrrect());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        setTextViewSuccessColor(getSpinnerTextView(), isSpinnerStateCorrrect());
    }
}
