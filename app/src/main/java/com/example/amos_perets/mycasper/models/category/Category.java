package com.example.amos_perets.mycasper.models.category;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.amos_perets.mycasper.fragment.FragmentExpensesList;
import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.dialog.ShowDialog;
import com.example.amos_perets.mycasper.fragment.FragmentManager;
import com.example.amos_perets.mycasper.manager_data.DataAction;
import com.example.amos_perets.mycasper.models.base_header.BaseHeader;
import com.example.amos_perets.mycasper.models.expense.Expense;
import com.example.amos_perets.mycasper.models.expense.IExpense;
import com.example.amos_perets.mycasper.utils.DateUtil;

import org.apache.commons.collections.map.ListOrderedMap;

import java.io.Serializable;

import io.reactivex.Observable;

public class Category extends BaseHeader
        implements ICategory, Serializable, Expense.ExpenseListener {

    public static final float LIMIT_TO_MONEY_ALMOST_OVER = 0.7f;

    private ListOrderedMap listOrderedMapIExpense;

    private CategoryListener categoryListener;

    private Context context;

    private double currAmountMoney;

    private FragmentManager fragmentManager;

    private boolean isStartFragmentExpensesList;

    public Category(Context context) {
        this.listOrderedMapIExpense = new ListOrderedMap();

        this.context = context;

        this.currAmountMoney = getCurrAmountMoney();

        this.fragmentManager = new FragmentManager();
    }

    @Override
    public void setCategory(String name, int amountMoney) {
        setName(name);
        setAmountMoney(amountMoney);
        onChangeCategory();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCurrAmountMoney() {
        int totalAmountMoney = 0;
        for (int i = 0; i < listOrderedMapIExpense.size(); i++) {
            totalAmountMoney += ((IExpense) listOrderedMapIExpense.getValue(i)).getAmountMoney();
        }
        return totalAmountMoney;
    }

    @Override
    public void createExpense() {
        Expense expense = new Expense(context);
        expense.setExpenseListener(this);
        new ShowDialog(context).show(expense, DataAction.ADD_NEW_EXPENSE);

    }

    @Override
    public void onLongClick() {
        isStartFragmentExpensesList = fragmentManager.startFragment(context, this);
    }

    @Override
    public String getOverLimitInPercent() {
        boolean isOverAmountMoney = this.getCurrAmountMoney() > this.getAmountMoney();
        return new StringBuilder()
                .append(isOverAmountMoney ? "(חריגה ב " : " (")
                .append(" ")
                .append(isOverAmountMoney
                        ? (((this.getCurrAmountMoney() - this.getAmountMoney()) / (double) this.getAmountMoney()) * 100)
                        : (((this.getCurrAmountMoney() / (double) this.getAmountMoney()) * 100)))
                .append(" ")
                .append(" אחוז מהתקציב)")
                .toString();
    }

    @Override
    public String getOverLimitInNumbers() {
        double div =(double)this.getCurrAmountMoney() / DateUtil.getNumberOfCurrentDay();
        double multiple = div * DateUtil.getNumberDaysInMonth();

        boolean isOverAmountMoney = multiple > this.getAmountMoney();

        return new StringBuilder()
                .append(isOverAmountMoney ? "אזהרת חריגה בסוף החודש ב" : "ניצלת")
                .append(" ")
                .append(isOverAmountMoney
                        ? multiple - this.getAmountMoney()
                        : this.getCurrAmountMoney())
                .append(" ")
                .append("ש'ח מהתקציב")
                .toString();
    }

    @Override
    public void onClickItem() {

    }

    @Override
    public void setAmountMoney(Integer amountMoney) {
        this.amountMoney = amountMoney;
    }

    @Override
    public void onClickEdit() {
        new ShowDialog(context).show(this, DataAction.EDIT_CATEGORY);
    }

    @Override
    public void onClickDelete() {
        if (categoryListener != null) {
            categoryListener.onRemoveCategory(this);
        }
    }

    @Override
    public int getCurrAmountMoneyTitleColor() {

        return currAmountMoney > amountMoney
                ? context.getResources().getColor(R.color.curr_amount_money_title_over_color)
                : (currAmountMoney / (double)amountMoney <= LIMIT_TO_MONEY_ALMOST_OVER
                        ? context.getResources().getColor(R.color.curr_amount_money_title_not_over_color)
                        :context.getResources().getColor(R.color.curr_amount_money_title_almost_over_color));

    }

    @Override
    public ListOrderedMap getIExpenseList() {
        return listOrderedMapIExpense;
    }

    @Override
    public void addToIExpenseList(IExpense iExpense) {
        listOrderedMapIExpense.put(iExpense.toString(), iExpense);
    }

    @Override
    public void removeFromIExpenseList(IExpense iExpense) {
        listOrderedMapIExpense.remove(iExpense);
    }

    private void onChangeCategory() {
        if(isStartFragmentExpensesList){
            fragmentManager.refreshFragment(this);
        }
        if (categoryListener != null) {
            categoryListener.addOrEditCategory(this);
        }
    }

    @Override
    public void setCategoryListener(CategoryListener categoryListener) {
        this.categoryListener = categoryListener;
    }

    @Override
    public void addOrEditExpense(IExpense iExpense) {
        if (listOrderedMapIExpense.get(iExpense.toString()) == null) {//edit
            listOrderedMapIExpense.put(iExpense.toString(), iExpense);
        }

        updateCurrAmountMoney();
        onChangeCategory();
    }

    @Override
    public void onRemoveExpense(IExpense iExpense) {
        listOrderedMapIExpense.remove(iExpense.toString());
        updateCurrAmountMoney();
        onChangeCategory();
    }




    private void updateCurrAmountMoney(){
        this.currAmountMoney = getCurrAmountMoney();
    }

    public interface CategoryListener {
        void addOrEditCategory(ICategory iCategory);
        void onRemoveCategory(ICategory iCategory);
    }
}

