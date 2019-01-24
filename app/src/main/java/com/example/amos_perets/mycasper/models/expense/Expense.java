package com.example.amos_perets.mycasper.models.expense;

import android.content.Context;

import com.example.amos_perets.mycasper.dialog.ShowDialog;
import com.example.amos_perets.mycasper.manager_data.DataAction;
import com.example.amos_perets.mycasper.models.base_header.BaseHeader;
import com.example.amos_perets.mycasper.models.category.ICategory;
import com.example.amos_perets.mycasper.utils.DateUtil;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Expense extends BaseHeader implements IExpense, Serializable {

    private String date = "";

    private Context context;

    private ExpenseListener expenseListener;

    public Expense(Context context) {
        this.context = context;
    }

    @Override
    public void setExpense(String name, int amountMoney, String date ) {
        setName(name);
        setAmountMoney(amountMoney);
        setDate(date.equals("")
                ? DateUtil.getCurrentDate() + "        " + DateUtil.getCurrentTime()
                : date);
        onChangeExpense();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAmountMoney(Integer amountMoney) {
        this.amountMoney = amountMoney;
    }

    @Override
    public void onClickEdit() {
        new ShowDialog(context).show(this, DataAction.EDIT_EXPENSE);
    }

    @Override
    public void onClickDelete() {
        if(expenseListener != null){
            expenseListener.onRemoveExpense(this);
        }
    }

    @Override
    public int getCurrAmountMoneyTitleColor() {
        return 0;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public void setDate(String date) {
        this.date = date;
    }

    private void onChangeExpense() {
        if(expenseListener != null){
            expenseListener.addOrEditExpense(this);
        }
    }

    @Override
    public void setExpenseListener(ExpenseListener expenseListener) {
        this.expenseListener = expenseListener;
    }

    public interface ExpenseListener{
        void addOrEditExpense(IExpense iExpense);
        void onRemoveExpense(IExpense iExpense);
    }

}
