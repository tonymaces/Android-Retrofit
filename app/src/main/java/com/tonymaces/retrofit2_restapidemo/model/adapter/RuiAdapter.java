package com.tonymaces.retrofit2_restapidemo.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tonymaces.retrofit2_restapidemo.R;
import com.tonymaces.retrofit2_restapidemo.model.Rui;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonym on 27/06/2016.
 */
public class RuiAdapter extends RecyclerView.Adapter<RuiAdapter.Holder> {

    private static final String TAG = RuiAdapter.class.getSimpleName();
    private List<Rui> mRuis;

    public  RuiAdapter(){
        mRuis = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.rui_item,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mRuis.size();
    }

    public void addRui(Rui rui) {
        Log.d(TAG,rui.getIdRuiMaster());
        mRuis.add(rui);
        notifyDataSetChanged();
    }

    public class Holder extends RecyclerView.ViewHolder  {

        public Holder(View itemView) {
            super(itemView);
        }
    }
}
