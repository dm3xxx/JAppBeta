package com.example.hello.japp;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class Tab1Input extends Fragment implements Button.OnClickListener, TextWatcher {

    private Button mColor, mSubmit;
    private EditText mDigit, mName;
    private String mDeColor;
    private boolean mHasColor, mHasDigit, mHasName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHasColor = mHasDigit = mHasName = false;
    }

    @Override
    public View onCreateView(LayoutInflater inf, ViewGroup vgc, Bundle savedInstanceState) {

        View v = inf.inflate(R.layout.fragment_tab1_input, vgc, false);

        mColor = v.findViewById(R.id.tab1_input_color_btn);
        mSubmit = v.findViewById(R.id.tab1_input_submit_btn);
        mDigit = v.findViewById(R.id.tab1_input_digit_input);
        mName = v.findViewById(R.id.tab1_input_name_input);

        mColor.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mDigit.addTextChangedListener(this);
        mName.addTextChangedListener(this);

        if (mHasColor)
            setColor(mDeColor);

        return v;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tab1_input_color_btn:
                ((TabOne) getParentFragment()).showPicker();
                break;

            case R.id.tab1_input_submit_btn:
                if (mHasName && mHasDigit && mHasColor) {
                    String name = mName.getText().toString();
                    String code = mDigit.getText().toString();
                    ((MainActivity) getActivity()).updateListPage(name, code, mDeColor);
                    resetField();
                }
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable e) {
        Editable digit = mDigit.getText(), name = mName.getText();
        String s = e.toString();

        if (digit.hashCode() == e.hashCode()) {
            if ((s.matches("\\d+")) && s.length() == 4) {
                mDigit.getBackground().clearColorFilter();
                mHasDigit = true;
            } else {
                mHasDigit = false;
                // check what is the default color and change it back
            }
        }

        if (name.hashCode() == e.hashCode()) {
            if (s.length() > 0) {
                mName.getBackground().clearColorFilter();
                mHasName = true;
            } else {
                mHasName = false;
                // check what is the default color and change it back
            }
        }

        validate();
    }

    private void resetField() {
        mDeColor = getResources().getString(R.string.default_color_str);
        mColor.setText(mDeColor);
        mDigit.setText("");
        mName.setText("");
        mHasColor = mHasDigit = mHasName = false;
    }

    public void setColor(String color) {
        mColor.setText(color);
        mDeColor = color;
        mHasColor = true;
        validate();
    }

    private void validate() {
        if (mHasColor && mHasDigit && mHasName) {
            mSubmit.setBackgroundColor(Color.BLUE);
        } else {
            mSubmit.setBackgroundResource(android.R.drawable.btn_default);
        }
    }
}