package com.example.amos_perets.mycasper.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.models.category.ICategory;
import com.example.amos_perets.mycasper.recycler.RecyclerView;
import com.example.amos_perets.mycasper.recycler_expense_list.AdapterExpenseList;
import com.example.amos_perets.mycasper.utils.AnimationUtil;
import com.example.amos_perets.mycasper.utils.DateUtil;

import org.apache.commons.collections.map.ListOrderedMap;

import java.io.Serializable;
import java.util.Locale;

import static com.example.amos_perets.mycasper.fragment.FragmentManager.CATEGORY_OBJECT;

public class FragmentExpensesList extends Fragment implements View.OnTouchListener, Serializable {
    float dX, dY;

    private View view;

    private TextView textViewTitle;

    private TextView textViewMoney;

    private TextView textViewNote;

    private RecyclerView recyclerViewListExpenses;

    private ListOrderedMap listOrderedMapExpenses;

    private AdapterExpenseList adapterExpenseList;

    private ICategory iCategory;

    private ValueAnimator valueAnimatorNote;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.expense_list_fragment, container, false);
        view.setOnTouchListener(this);
        ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).topMargin =
                getActivity().findViewById(R.id.toolbar).getMeasuredHeight();

        iCategory = (ICategory) getArguments().getSerializable(CATEGORY_OBJECT);

        findViews();
        initRecyclerView();
        setFragmentFieldsByModel(iCategory);
        return view;
    }

    public void setFragmentFieldsByModel(ICategory iCategory) {
        if (iCategory != null) {
            textViewTitle.setText(iCategory.getName());
            textViewMoney.setText(String.valueOf(iCategory.getAmountMoney()));
            valueAnimatorNote = AnimationUtil.animateStringsArray(
                    textViewNote, 2000, iCategory.getOverLimitInNumbers(), iCategory.getOverLimitInPercent());

            listOrderedMapExpenses = iCategory.getIExpenseList();
            adapterExpenseList.updateList(listOrderedMapExpenses);
        }
    }

    private void findViews() {
        textViewTitle = view.findViewById(R.id.text_view_title_expenses_list);
        textViewMoney = view.findViewById(R.id.text_view_title_amount_money);
        textViewNote = view.findViewById(R.id.text_view_note);
    }

    private void initRecyclerView(){
        recyclerViewListExpenses = view.findViewById(R.id.recycler_expenses_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapterExpenseList = new AdapterExpenseList();
        recyclerViewListExpenses.setLayoutManager(linearLayoutManager);
        recyclerViewListExpenses.setAdapter(adapterExpenseList);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        valueAnimatorNote.cancel();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

//                dX = view.getX() - event.getRawX();
                dY = view.getY() - event.getRawY();
                break;

            case MotionEvent.ACTION_MOVE:
                view.setY(event.getRawY() + dY);
                view.invalidate();
                break;
            default:
                return false;
        }
        return true;
    }

}
