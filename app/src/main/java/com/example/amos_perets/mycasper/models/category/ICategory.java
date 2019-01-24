package com.example.amos_perets.mycasper.models.category;

import com.example.amos_perets.mycasper.models.base_header.IBaseHeader;
import com.example.amos_perets.mycasper.models.expense.IExpense;
import com.example.amos_perets.mycasper.models.header.Header;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.List;
import java.util.Observable;

public interface ICategory extends IBaseHeader {

    ListOrderedMap getIExpenseList();

    void addToIExpenseList(IExpense iExpense);

    void removeFromIExpenseList(IExpense iExpense);

    void setCategoryListener(Category.CategoryListener categoryListener);

    void setCategory(String name, int amountMoney);

    int getCurrAmountMoney();

    void createExpense();

    void onLongClick();

    String getOverLimitInPercent();

    String getOverLimitInNumbers();
}
