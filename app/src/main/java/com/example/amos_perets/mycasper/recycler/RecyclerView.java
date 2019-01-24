package com.example.amos_perets.mycasper.recycler;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class RecyclerView extends android.support.v7.widget.RecyclerView implements IRecyclerViewEvents{

    public static final float SCALE_LARGE = 1.07f;
    public static final float SCALE_SMALL = 0.5f;

    private float scale;

    public RecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void longClickOnItem(View view, String strAction, int action) {

    }

    @Override
    public void clickOnItem(View view, String strAction, int action, int position) {


    }

    @Override
    public void clickOnDeleteItem(View view, String strAction, int action, int position) {

    }

    @Override
    public void clickOnEditItem(View view, String strAction, int action, int position) {

    }

}
