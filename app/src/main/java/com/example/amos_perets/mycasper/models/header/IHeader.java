package com.example.amos_perets.mycasper.models.header;

import com.example.amos_perets.mycasper.models.base_header.IBaseHeader;
import com.example.amos_perets.mycasper.models.category.Category;
import com.example.amos_perets.mycasper.models.category.ICategory;

import org.apache.commons.collections.map.ListOrderedMap;

import io.reactivex.Single;

public interface IHeader extends IBaseHeader {

    public ListOrderedMap getICategoryList();

    public void setHeaderListener(Header.HeaderListener headerListener);

    public void createCategory();
}
