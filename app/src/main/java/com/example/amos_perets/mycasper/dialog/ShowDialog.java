package com.example.amos_perets.mycasper.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.base_header.IBaseHeader;
import com.example.amos_perets.mycasper.manager_data.DataAction;

import java.util.ArrayList;
import java.util.List;

public class ShowDialog extends Dialog implements View.OnClickListener {

    /**
     * title of dialog
     */
    private TextView tvTitle;

    /**
     * edit text of name
     */
    private EditText etEnterName;

    /**
     * edit text of amount money
     */
    private EditText etEnterAmountMoney;

    /**
     * add the input
     */
    private Button btnAdd;

    /**
     * model
     */
    private IBaseHeader baseHeader;

    /**
     * action add / edit
     */
    private DataAction dataAction;

    /**
     * object
     */
    private Object object;

    private static List<DialogListener> dialogListenerList = new ArrayList<>();

    public ShowDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_add);

        findViews();
        initViews();

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

    }

    /**
     * init all views
     */
    private void initViews() {
        btnAdd.setOnClickListener(this);
    }

    /**
     * find all views
     */
    private void findViews() {
        etEnterName = findViewById(R.id.edit_text_enter_name);
        etEnterAmountMoney = findViewById(R.id.edit_text_enter_money);
        btnAdd = findViewById(R.id.button_add);
        tvTitle = findViewById(R.id.dialog_title);
    }

    public void show(Object obj, DataAction dataAction) {
        this.object = obj;
        this.dataAction = dataAction;
        baseHeader = (IBaseHeader) obj;
        setEtByObject();
        tvTitle.setText(DataAction.ADD_NEW_HEADER == dataAction
                ? "Add header"
                : (DataAction.ADD_NEW_CATEGORY == dataAction ? " Add category" : "Add expense"));
        super.show();
    }

    private void setEtByObject() {
        etEnterAmountMoney.setVisibility(baseHeader.isAmountMoneyCalculated() ? View.GONE : View.VISIBLE);
        etEnterAmountMoney.setText(baseHeader.getAmountMoney() == 0 ? "" : "" + baseHeader.getAmountMoney());
        etEnterName.setText(baseHeader.getName());
    }

    @Override
    public void onClick(View v) {
        if (etEnterName.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), "enter name ", Toast.LENGTH_SHORT).show();
        } else if ((dataAction != DataAction.ADD_NEW_HEADER && dataAction != DataAction.EDIT_HEADER)
                && (etEnterAmountMoney.getText().toString().isEmpty() || etEnterAmountMoney.getText().toString().charAt(0) == '0')) {
            Toast.makeText(getContext(), "enter money ", Toast.LENGTH_SHORT).show();
        } else {
            notifyOnClick();
            etEnterName.setText("");
            etEnterAmountMoney.setText("");
            dismiss();
        }
    }

    private void notifyOnClick() {
        for (DialogListener dialogListener : dialogListenerList) {
            dialogListener.onClickDialogAdd(
                    etEnterName.getText().toString()
                    , etEnterAmountMoney.getText().toString()
                    , object
                    , dataAction);
        }
    }

    public static void addDialogListener(DialogListener dialogListener) {
        dialogListenerList.add(dialogListener);
    }

    public interface DialogListener {
        public void onClickDialogAdd(String etName, String etAmountMoney, Object object, DataAction dataAction);
    }

}
