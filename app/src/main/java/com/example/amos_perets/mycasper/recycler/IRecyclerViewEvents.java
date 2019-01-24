package com.example.amos_perets.mycasper.recycler;

import android.view.View;

public interface IRecyclerViewEvents {

    void longClickOnItem(View view, String strAction, int action);

    void clickOnItem(View view, String strAction, int action, int position);

    void clickOnDeleteItem(View view, String strAction, int action, int position);

    void clickOnEditItem(View view, String strAction, int action, int position);



}
