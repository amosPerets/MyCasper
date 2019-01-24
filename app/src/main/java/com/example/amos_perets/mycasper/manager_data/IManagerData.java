package com.example.amos_perets.mycasper.manager_data;

import com.example.amos_perets.mycasper.models.header.IHeader;

import org.apache.commons.collections.map.ListOrderedMap;

public interface IManagerData {

    public ListOrderedMap getListIHeaders();
    public void createHeader();
}
