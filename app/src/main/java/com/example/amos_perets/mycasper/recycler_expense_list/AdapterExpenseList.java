package com.example.amos_perets.mycasper.recycler_expense_list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.category.ICategory;
import com.example.amos_perets.mycasper.models.expense.IExpense;
import com.example.amos_perets.mycasper.recycler.IRecyclerViewEvents;
import com.example.amos_perets.mycasper.recycler_main_category.MainCategoryViewHolder;

import org.apache.commons.collections.map.ListOrderedMap;

public class AdapterExpenseList extends RecyclerView.Adapter {

    private ListOrderedMap listOrderedMapIExpense = new ListOrderedMap();

    public AdapterExpenseList() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expense, parent, false);
        return new ExpenseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int index) {
        ((ExpenseListViewHolder) viewHolder).bindData((IExpense) listOrderedMapIExpense.getValue(index));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return listOrderedMapIExpense.size();
    }

    public void updateList(ListOrderedMap listOrderedMapIExpense){
        this.listOrderedMapIExpense = listOrderedMapIExpense;
        notifyDataSetChanged();
    }
}
