package com.example.amos_perets.mycasper.recycler_main_category;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.amos_perets.mycasper.R;
import com.example.amos_perets.mycasper.recycler.IRecyclerViewEvents;
import com.example.amos_perets.mycasper.models.category.ICategory;

import org.apache.commons.collections.map.ListOrderedMap;

public class AdapterMainCategory extends RecyclerView.Adapter {

    private ListOrderedMap listOrderedMapICategory = new ListOrderedMap();


    public AdapterMainCategory() {
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new MainCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int index) {
        ((MainCategoryViewHolder) viewHolder).bindData((ICategory) listOrderedMapICategory.getValue(index));
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return listOrderedMapICategory.size();
    }

    public void updateList( ListOrderedMap ListOrderedMapICategory) {
        this.listOrderedMapICategory = ListOrderedMapICategory;
        notifyDataSetChanged();
    }
}
