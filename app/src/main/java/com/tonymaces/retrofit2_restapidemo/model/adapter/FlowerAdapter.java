package com.tonymaces.retrofit2_restapidemo.model.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tonymaces.retrofit2_restapidemo.R;
import com.tonymaces.retrofit2_restapidemo.model.Flower;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonym on 29/06/2016.
 */
public class FlowerAdapter  extends RecyclerView.Adapter<FlowerAdapter.Holder>  {
    private final FlowerClickListener mListener;
    private List<Flower> mFlowers;
    private static final String TAG = FlowerAdapter.class.getSimpleName();

    public  FlowerAdapter(FlowerClickListener listener){
        mFlowers = new ArrayList<>();
        mListener = listener;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.flower_item,parent,false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Flower currFlower = mFlowers.get(position);
        holder.mName.setText(currFlower.getName());
        holder.mPrice.setText("$ "+Double.toString(currFlower.getPrice()));
        Picasso.with(holder.itemView.getContext()).load("http://services.hanselandpetal.com/photos/" + currFlower.getPhoto()).into(holder.mPhoto);
    }

    @Override
    public int getItemCount() {
        return  mFlowers.size();
    }

    public void addFlower(Flower flower) {
        Log.d(TAG,flower.getPhoto());
        mFlowers.add(flower);
        notifyDataSetChanged();
    }

    public Flower getSelectedFlower(int position) {
        return  mFlowers.get(position);
    }

    public class Holder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView mPhoto;
        private TextView mName, mPrice;

        public Holder(View itemView) {
            super(itemView);
            mPhoto = (ImageView) itemView.findViewById(R.id.flowerPhoto);
            mName = (TextView) itemView.findViewById(R.id.flowerName);
            mPrice = (TextView) itemView.findViewById(R.id.flowerPrice);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mListener.onClick(getLayoutPosition());
        }
    }

    public  interface FlowerClickListener{
        void onClick(int position);
    }

}
