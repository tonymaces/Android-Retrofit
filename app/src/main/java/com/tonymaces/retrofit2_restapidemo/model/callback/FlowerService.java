package com.tonymaces.retrofit2_restapidemo.model.callback;

import com.tonymaces.retrofit2_restapidemo.model.Flower;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tonym on 29/06/2016.
 */
public interface FlowerService {
    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();
}
