package com.example.amos_perets.mycasper.dialog;

import com.example.amos_perets.mycasper.models.category.Category;
import com.example.amos_perets.mycasper.models.expense.Expense;
import com.example.amos_perets.mycasper.models.header.Header;
import com.example.amos_perets.mycasper.models.header.IHeader;
import com.example.amos_perets.mycasper.manager_data.DataAction;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ShowDialogResult implements ShowDialog.DialogListener {

    public ShowDialogResult() {
        ShowDialog.addDialogListener(this);
    }

    @Override
    public void onClickDialogAdd(String titleName, String amountMoney, Object object, DataAction dataAction) {
        switch (dataAction) {
            case ADD_NEW_HEADER:
            case EDIT_HEADER:
                ((Header) object).setName(titleName);
                break;

            case ADD_NEW_CATEGORY:
            case EDIT_CATEGORY:
                ((Category) object).setCategory(titleName, Integer.valueOf(amountMoney));
                break;

            case ADD_NEW_EXPENSE:
            case EDIT_EXPENSE:
                ((Expense) object).setExpense(titleName, Integer.valueOf(amountMoney), ((Expense) object).getDate());
                break;
        }
    }

}
