package com.example.amos_perets.mycasper.recycler_main_category;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.category.ICategory;

public class MainCategoryViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {
    private static final long VIEW_ANIMATE_DURATION = 300;

    private ICategory iCategory;

    private TextView tvSubject;

    private TextView tvAmountMoney;

    private ImageView ivDelete;

    private ImageView ivEdit;

    private float scaleIcon;

    private TextView tvCurrAmountMoney;

    public MainCategoryViewHolder(View itemView) {
        super(itemView);

        bindViews();
        initViews();
    }

    private void bindViews() {

        tvSubject = itemView.findViewById(R.id.text_title);
        tvAmountMoney = itemView.findViewById(R.id.text_amount_money);
        ivDelete = itemView.findViewById(R.id.button_delete);
        ivEdit = itemView.findViewById(R.id.button_edit);
        tvCurrAmountMoney = itemView.findViewById(R.id.text_curr_amount_money);

    }

    private void initViews() {
        ivDelete.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        tvSubject.setOnClickListener(this);
        tvAmountMoney.setOnClickListener(this);
        tvCurrAmountMoney.setOnClickListener(this);
        tvSubject.setOnLongClickListener(this);
        tvAmountMoney.setOnLongClickListener(this);
        tvCurrAmountMoney.setOnLongClickListener(this);
        ivDelete.setOnTouchListener(this);
        ivEdit.setOnTouchListener(this);
        tvSubject.setFocusable(true);
        tvSubject.setSelected(true);
        tvSubject.setClickable(true);
    }

    public void bindData(final ICategory iCategory) {
        this.iCategory = iCategory;
        tvSubject.setText(iCategory.getName());
        tvAmountMoney.setText(String.valueOf(iCategory.getAmountMoney()));
        tvCurrAmountMoney.setText(String.valueOf(iCategory.getCurrAmountMoney()));
        tvCurrAmountMoney.setTextColor(iCategory.getCurrAmountMoneyTitleColor());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.button_edit:
                iCategory.onClickEdit();
                break;

            case R.id.button_delete:
                iCategory.onClickDelete();
                break;

            case R.id.text_title:
            case R.id.text_curr_amount_money:
            case R.id.text_amount_money:
                iCategory.createExpense();
                break;

        }

    }

    @Override
    public boolean onLongClick(View v) {
        iCategory.onLongClick();
        return true;
    }


    private void animateView(View view) {
        ValueAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0.6f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0.6f, 1f));
        scaleAnimator.setDuration(VIEW_ANIMATE_DURATION);
        scaleAnimator.start();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            scaleIcon = 1.2f;
        } else if(event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP){
            scaleIcon = 1f;
        }

        switch (view.getId()) {
            case R.id.button_edit:
//                view.animate().scaleY(scaleIcon).scaleX(scaleIcon).setDuration(200).start();
                view.setScaleY(scaleIcon);
                view.setScaleX(scaleIcon);
                break;

            case R.id.button_delete:
//                view.animate().scaleY(scaleIcon).scaleX(scaleIcon).setDuration(200).start();
                view.setScaleY(scaleIcon);
                view.setScaleX(scaleIcon);
                break;
        }

        return false;
    }
}
