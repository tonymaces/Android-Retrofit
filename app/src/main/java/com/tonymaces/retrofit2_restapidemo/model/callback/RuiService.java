package com.tonymaces.retrofit2_restapidemo.model.callback;

import com.tonymaces.retrofit2_restapidemo.model.Rui;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tonym on 28/06/2016.
 */
public interface RuiService {
    //@GET("/api/RuiSearch/Rossi.json")
    @GET("/api/RuiSearch/A000445012")
    Call<List<Rui>> getAllRuis();
}
