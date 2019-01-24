package com.example.amos_perets.mycasper.manager_data;

import android.content.Context;

import com.example.amos_perets.mycasper.dialog.ShowDialog;
import com.example.amos_perets.mycasper.models.header.Header;
import com.example.amos_perets.mycasper.models.header.IHeader;
import com.example.amos_perets.mycasper.utils.ApplicationUtil;

import org.apache.commons.collections.map.ListOrderedMap;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public class ManagerData implements
        IManagerData, Header.HeaderListener {

    private ListOrderedMap listOrderedMapIHeaders;
    private PublishSubject<ListOrderedMap> listOrderedMapPublishSubject = PublishSubject.create();
    private Context context;

    public ManagerData(Context context) {
        this.listOrderedMapIHeaders = new ListOrderedMap();
        this.context = context;
    }

    @Override
    public ListOrderedMap getListIHeaders() {
        return listOrderedMapIHeaders;
    }

    @Override
    public void addOrEditHeader(IHeader iHeader) {
//        if(listOrderedMapIHeaders.get(iHeader.toString()) != null){//edit
//            listOrderedMapIHeaders.setValue(listOrderedMapIHeaders.indexOf(iHeader.toString()), iHeader);
//        } else {//add
//            listOrderedMapIHeaders.put(iHeader.toString(), iHeader);
//        }
        if(listOrderedMapIHeaders.get(iHeader.toString()) == null){//edit
            listOrderedMapIHeaders.put(iHeader.toString(), iHeader);
        } 
        listOrderedMapPublishSubject.onNext(listOrderedMapIHeaders);

    }

    @Override
    public void onRemoveHeader(IHeader iHeader) {
        listOrderedMapIHeaders.remove(iHeader.toString());
        listOrderedMapPublishSubject.onNext(listOrderedMapIHeaders);
    }

    @Override
    public void createHeader(){
        Header header = new Header(context);
        header.setHeaderListener(this);
        new ShowDialog(context).show(header, DataAction.ADD_NEW_HEADER);
    }

    public Observable<ListOrderedMap> getObservableListIHeaders(){
        return listOrderedMapPublishSubject.hide()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
