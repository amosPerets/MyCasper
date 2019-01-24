package com.example.amos_perets.mycasper.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.category.Category;
import com.example.amos_perets.mycasper.models.category.ICategory;

public class FragmentManager {

    /**
     * object category, pass to fragment expenses list
     */
    public static final String CATEGORY_OBJECT = "CATEGORY_OBJECT";

    private  FragmentExpensesList fragmentExpensesList;

    public FragmentManager() {
        fragmentExpensesList = new FragmentExpensesList();
    }

    public  boolean startFragment(Context context, Object object) {
        if (object instanceof Category) {
             startFragmentExpensesList(context, object);
        }

        return true;
    }

    private void startFragmentExpensesList(Context context, Object object) {

        Bundle bundle = new Bundle();
        bundle.putSerializable(CATEGORY_OBJECT, (Category) object);
        fragmentExpensesList.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_activity, fragmentExpensesList, "ADD")
                .addToBackStack("ADD")
                .commit();
    }

    public void refreshFragment(Object object){
        fragmentExpensesList.setFragmentFieldsByModel((ICategory) object);
    }
}
