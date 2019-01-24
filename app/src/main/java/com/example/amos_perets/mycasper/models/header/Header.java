package com.example.amos_perets.mycasper.models.header;

import android.content.Context;

import com.example.amos_perets.mycasper.dialog.ShowDialog;
import com.example.amos_perets.mycasper.manager_data.DataAction;
import com.example.amos_perets.mycasper.models.base_header.BaseHeader;
import com.example.amos_perets.mycasper.models.category.Category;
import com.example.amos_perets.mycasper.models.category.ICategory;

import org.apache.commons.collections.map.ListOrderedMap;

public class Header extends BaseHeader implements IHeader, Category.CategoryListener{

    private ListOrderedMap listOrderedMapICategory;

    private HeaderListener headerListener;

    private Context context;

    public Header(Context context) {
        this.listOrderedMapICategory = new ListOrderedMap();
        this.context = context;
    }

    @Override
    public void setName(String name) {
        this.name = name;
        onChangeHeader();
    }

    @Override
    public void setAmountMoney(Integer amountMoney) {
        this.amountMoney += amountMoney;
    }

    private void updateAmountMoney() {
        this.amountMoney = getTotalAmountMoneyCategories();
    }

    @Override
    public ListOrderedMap getICategoryList() {
        return listOrderedMapICategory;
    }

    @Override
    public void onClickEdit() {
        new ShowDialog(context).show(this, DataAction.EDIT_HEADER);
    }

    @Override
    public void onClickDelete() {
        if(headerListener != null){
            headerListener.onRemoveHeader(this);
        }
    }

    @Override
    public int getCurrAmountMoneyTitleColor() {
        return 0;
    }

    @Override
    public void onClickItem() {

    }

    private int getTotalAmountMoneyCategories() {
        int totalAmountMoney = 0;
        for (int i = 0; i < listOrderedMapICategory.size(); i++) {
            totalAmountMoney += ((ICategory) listOrderedMapICategory.getValue(i)).getAmountMoney();
        }
        return totalAmountMoney;
    }

    @Override
    public void createCategory() {
        Category category = new Category(context);
        category.setCategoryListener(this);
        new ShowDialog(context).show(category, DataAction.ADD_NEW_CATEGORY);
    }

    @Override
    public void addOrEditCategory(ICategory iCategory) {
        if(listOrderedMapICategory.get(iCategory.toString()) == null){//edit
            listOrderedMapICategory.put(iCategory.toString(), iCategory);
        }
        updateAmountMoney();
        onChangeHeader();
    }

    private void onChangeHeader() {
        if(headerListener != null){
            headerListener.addOrEditHeader(this);
        }
    }

    @Override
    public void onRemoveCategory(ICategory iCategory) {
        listOrderedMapICategory.remove(iCategory.toString());
        setAmountMoney(-iCategory.getAmountMoney());
        onChangeHeader();
    }

    @Override
    public void setHeaderListener(HeaderListener headerListener){
        this.headerListener = headerListener;
    }

    @Override
    public boolean isAmountMoneyCalculated() {
        return true;
    }

    public interface HeaderListener{
        void addOrEditHeader(IHeader iHeader);
        void onRemoveHeader(IHeader iHeader);
    }
}