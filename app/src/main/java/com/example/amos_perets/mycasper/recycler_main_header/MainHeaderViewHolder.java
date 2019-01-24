package com.example.amos_perets.mycasper.recycler_main_header;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.header.IHeader;
import com.example.amos_perets.mycasper.recycler_main_category.AdapterMainCategory;

public class MainHeaderViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener, View.OnTouchListener {

    private static final long VIEW_ANIMATE_DURATION = 300;

    private IHeader iHeader;

    private TextView tvSubject;

    private TextView tvAmountMoney;

    private ImageView ivDelete;

    private ImageView ivEdit;

    private float scaleIcon;

    private Button btnAddCategory;

    private AdapterMainCategory adapterMainCategory;

    public MainHeaderViewHolder(View itemView) {
        super(itemView);

        bindViews();
        initViews();
        initRecyclerView();
        tvSubject.setFocusable(true);
        tvSubject.setSelected(true);
        tvSubject.setClickable(true);
    }

    public void bindData(final IHeader iHeader) {
        this.iHeader = iHeader;

        tvSubject.setText(iHeader.getName());
        tvAmountMoney.setText(String.valueOf(iHeader.getAmountMoney()));
        adapterMainCategory.updateList(iHeader.getICategoryList());
    }

    private void initViews() {
        ivDelete.setOnClickListener(this);
        ivEdit.setOnClickListener(this);
        ivDelete.setOnTouchListener(this);
        ivEdit.setOnTouchListener(this);
        btnAddCategory.setOnClickListener(this);
        tvSubject.setSelected(true);
    }

    private void bindViews() {
        tvSubject = itemView.findViewById(R.id.text_title);
        tvAmountMoney = itemView.findViewById(R.id.text_amount_money);
        ivDelete = itemView.findViewById(R.id.button_delete);
        ivEdit = itemView.findViewById(R.id.button_edit);
        btnAddCategory = itemView.findViewById(R.id.butt_add_new_category);

    }

    private void initRecyclerView() {
        RecyclerView recyclerViewCategory = itemView.findViewById(R.id.recycler_category_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(itemView.getContext());
        recyclerViewCategory.setLayoutManager(linearLayoutManager);
        adapterMainCategory = new AdapterMainCategory();
        recyclerViewCategory.setAdapter(adapterMainCategory);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_edit:
//                animateView(view);
                iHeader.onClickEdit();
                break;

            case R.id.button_delete:
                iHeader.onClickDelete();
                break;

            case R.id.butt_add_new_category:
                iHeader.createCategory();
                break;
        }

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            scaleIcon = 0.5f;
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

    private void animateView(View view) {
        ValueAnimator scaleAnimator = ObjectAnimator.ofPropertyValuesHolder(view,
                PropertyValuesHolder.ofFloat("scaleX", 1f, 0.6f, 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f, 0.6f, 1f));
        scaleAnimator.setDuration(VIEW_ANIMATE_DURATION);
        scaleAnimator.start();
    }

}
