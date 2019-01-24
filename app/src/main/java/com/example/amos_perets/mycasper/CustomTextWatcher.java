package com.example.amos_perets.mycasper;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class  CustomTextWatcher implements TextWatcher {

    private EditText et;

    private String strToUpdate;

    public CustomTextWatcher(EditText et, String strToUpdate) {
        this.et = et;
        et.addTextChangedListener(this);
        this.strToUpdate = strToUpdate;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        strToUpdate = s.toString();
    }
}