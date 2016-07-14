package com.tonymaces.retrofit2_restapidemo.controller;

import com.tonymaces.retrofit2_restapidemo.model.callback.FlowerService;
import com.tonymaces.retrofit2_restapidemo.model.callback.RuiService;
import com.tonymaces.retrofit2_restapidemo.model.helper.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tonym on 28/06/2016.
 */
public class RestManager {

    private RuiService mRuiService;
    private FlowerService mFlowerService;

    public RuiService getRuiService(){
        if (mRuiService ==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL_RUI)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mRuiService = retrofit.create(RuiService.class);
        }
        return mRuiService;
    }

    public FlowerService getFlowerService(){
        if (mFlowerService ==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mFlowerService = retrofit.create(FlowerService.class);
        }
        return mFlowerService;
    }
}
