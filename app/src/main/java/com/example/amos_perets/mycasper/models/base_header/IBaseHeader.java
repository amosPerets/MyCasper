package com.example.amos_perets.mycasper.models.base_header;

public interface IBaseHeader {

    String getName();

    Integer getAmountMoney();

    void setName(String name);

    void setAmountMoney(Integer amountMoney);

    void onClickEdit();

    void onClickDelete();

    int getCurrAmountMoneyTitleColor();

    boolean isEmpty();

    default boolean isAmountMoneyCalculated(){
        return false;
    };

    void onClickItem();

}
