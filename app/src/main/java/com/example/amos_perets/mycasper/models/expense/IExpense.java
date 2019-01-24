package com.example.amos_perets.mycasper.models.expense;

import com.example.amos_perets.mycasper.models.base_header.IBaseHeader;

public interface IExpense extends IBaseHeader {

    public String getDate();

    public void setDate(String date);

    public void setExpenseListener(Expense.ExpenseListener expenseListener);

    public void setExpense(String name, int amountMoney, String date);

}
