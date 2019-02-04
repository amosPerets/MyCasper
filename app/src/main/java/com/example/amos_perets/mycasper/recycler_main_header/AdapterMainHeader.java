package com.example.amos_perets.mycasper.recycler_main_header;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.manager_data.ManagerData;
import com.example.amos_perets.mycasper.models.header.IHeader;

import org.apache.commons.collections.map.ListOrderedMap;

import java.util.ArrayList;
import java.util.List;

public class AdapterMainHeader extends RecyclerView.Adapter {

    private ListOrderedMap listOrderedMapIHeaders = new ListOrderedMap();

    private List<ChangeShowCategoriesListener> changeShowCategoriesListeners = new ArrayList<>();

    private boolean isOpen;

    public AdapterMainHeader(ManagerData managerData) {
        managerData.getObservableListIHeaders()
                .subscribe(AdapterMainHeader.this::updateList);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header_and_category_list, parent, false);

        return addChangeShowCategoriesListener(new MainHeaderViewHolder(view, isOpen));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int index) {
        ((MainHeaderViewHolder) viewHolder).bindData(((IHeader)(listOrderedMapIHeaders.getValue(index))));
    }

    public void changeShowCategories(boolean status){
        this.isOpen = status;
        notifyChangeShowCategories(status);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return listOrderedMapIHeaders.size();
    }

    private void updateList(ListOrderedMap listOrderedMapIHeadersList) {
        this.listOrderedMapIHeaders = listOrderedMapIHeadersList;
        notifyDataSetChanged();
    }

    private void notifyChangeShowCategories(boolean status){
        for(ChangeShowCategoriesListener changeShowCategoriesListener : changeShowCategoriesListeners){
            changeShowCategoriesListener.onChangeShowCategories(status);
        }
    }

    public MainHeaderViewHolder addChangeShowCategoriesListener(MainHeaderViewHolder mainHeaderViewHolder){
        changeShowCategoriesListeners.add(mainHeaderViewHolder);
        return mainHeaderViewHolder;
    }

    public interface ChangeShowCategoriesListener{
         void onChangeShowCategories(boolean status);
    }
}
