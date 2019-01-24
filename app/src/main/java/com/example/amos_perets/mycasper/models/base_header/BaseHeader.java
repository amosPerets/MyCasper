package com.example.amos_perets.mycasper.models.base_header;

public class BaseHeader implements IBaseHeader {

    protected String name;
    protected int amountMoney;

    public BaseHeader() {
    }

    public BaseHeader(String name, int amountMoney) {
        this.name = name;
        this.amountMoney = amountMoney;
    }



    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getAmountMoney() {
        return amountMoney;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setAmountMoney(Integer amountMoney) {
        this.amountMoney = amountMoney;
    }

    @Override
    public void onClickEdit() {

    }

    @Override
    public void onClickDelete() {

    }

    @Override
    public int getCurrAmountMoneyTitleColor() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onClickItem() {

    }
}
