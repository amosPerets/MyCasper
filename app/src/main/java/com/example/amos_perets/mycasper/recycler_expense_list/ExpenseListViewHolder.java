package com.example.amos_perets.mycasper.recycler_expense_list;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.category.ICategory;
import com.example.amos_perets.mycasper.models.expense.IExpense;
import com.example.amos_perets.mycasper.recycler.IRecyclerViewEvents;

public class ExpenseListViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener{
    private static final long VIEW_ANIMATE_DURATION = 300;

    private IExpense iExpense;

    private TextView tvSubject;

    private TextView tvAmountMoney;

    private ImageView ivDelete;

    private ImageView ivEdit;

    private TextView tvDate;

    public void bindData(final IExpense iExpense) {
        Log.d("CHECK_RECYCLER", "bindData Category");
        this.iExpense = iExpense;
        tvSubject.setText(iExpense.getName());
        tvAmountMoney.setText(String.valueOf(iExpense.getAmountMoney()));
        tvDate.setText(iExpense.getDate());
    }

    public ExpenseListViewHolder(View itemView) {
        super(itemView);

        tvSubject = itemView.findViewById(R.id.text_title);
        tvAmountMoney = itemView.findViewById(R.id.text_amount_money);
        ivDelete = itemView.findViewById(R.id.button_delete);
        ivEdit = itemView.findViewById(R.id.button_edit);

        tvDate = itemView.findViewById(R.id.text_date);

        ivDelete.setOnClickListener(this);
        ivEdit.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_edit:
                animateView(view);
                iExpense.onClickEdit();
                break;

            case R.id.button_delete:
                iExpense.onClickDelete();
                break;

        }
    }
    private void animateView(View view){
        ValueAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0.6f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0.6f, 1f));
        scaleAnimator.setDuration(VIEW_ANIMATE_DURATION);
        scaleAnimator.start();
    }
}
