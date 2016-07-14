package com.tonymaces.retrofit2_restapidemo.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.tonymaces.retrofit2_restapidemo.R;
import com.tonymaces.retrofit2_restapidemo.controller.RestManager;
import com.tonymaces.retrofit2_restapidemo.model.Flower;
import com.tonymaces.retrofit2_restapidemo.model.Rui;
import com.tonymaces.retrofit2_restapidemo.model.adapter.FlowerAdapter;
import com.tonymaces.retrofit2_restapidemo.model.adapter.RuiAdapter;
import com.tonymaces.retrofit2_restapidemo.model.helper.Constants;
import com.tonymaces.retrofit2_restapidemo.model.helper.FlowerDatabase;
import com.tonymaces.retrofit2_restapidemo.model.helper.Utils;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements FlowerAdapter.FlowerClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private RestManager mManager;
    private RuiAdapter mRuiAdapter;
    private FlowerAdapter mFlowerAdapter;
    private FlowerDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configFlowerViews();
        mManager = new RestManager();
        mDatabase = new FlowerDatabase(this);
        if (Utils.isNetworkAvailable(getApplicationContext())){
            callFlowersRest();
        }else{
            callFlowersDatabase();
        }
    }

    private void callFlowersDatabase() {
        List<Flower> flowers = mDatabase.getFlowers();
        for(int i = 0; i < flowers.size();  i ++ ){
            Flower flower = flowers.get(i);
            Log.d(TAG, flower.getName() + "||" + flower.getInstructions());
        }
    }

    private void configRuiViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        mRuiAdapter = new RuiAdapter();
        mRecyclerView.setAdapter(new RuiAdapter());
    }

    private void configFlowerViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setRecycledViewPool(new RecyclerView.RecycledViewPool());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        mFlowerAdapter = new FlowerAdapter(this);
        mRecyclerView.setAdapter(mFlowerAdapter);
    }

    private  void  callFlowersRest(){
        Call<List<Flower>> listCall = mManager.getFlowerService().getAllFlowers();
        listCall.enqueue(new Callback<List<Flower>>() {

            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                if(response.isSuccessful()){
                    List<Flower> flowerList = response.body();
                    for (int i = 0; i < flowerList.size(); i++) {
                        Flower flower = flowerList.get(i);

                        SaveIntoDatabase task = new SaveIntoDatabase();
                        task.execute(flower);

                        mFlowerAdapter.addFlower(flower);
                    }
                }
                else{
                    int sc = response.code();
                    switch (sc){

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {

            }
        });
    }

    private  void  callRui(){
        Call<List<Rui>> listCall = mManager.getRuiService().getAllRuis();
        listCall.enqueue(new Callback<List<Rui>>() {
            @Override
            public void onResponse(Call<List<Rui>> call, Response<List<Rui>> response) {
                if(response.isSuccessful()){
                    List<Rui> ruiList = response.body();
                    for (int i = 0; i < ruiList.size(); i++) {
                        Rui rui = ruiList.get(i);
                        mRuiAdapter.addRui(rui);
                    }
                }
                else{
                    int sc = response.code();
                    switch (sc){

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Rui>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(int position) {
        Flower selectedFlower = mFlowerAdapter.getSelectedFlower(position);
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra(Constants.REFERENCE.FLOWER, selectedFlower);
        startActivity(intent);
    }

    public  class SaveIntoDatabase extends AsyncTask<Flower, Flower, Boolean> {

        private  final  String TAG = SaveIntoDatabase.class.getSimpleName();

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Flower... params) {
            Flower flower = params[0];
            try {
                InputStream stream = new URL("http://services.hanselandpetal.com/photos/" + flower.getPhoto()).openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(stream);
                flower.setPicture(bitmap);
                publishProgress(flower);
            }catch (Exception ex){
                Log.d(TAG,ex.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Flower... values) {
            super.onProgressUpdate(values);
            mDatabase.addFlower(values[0]);
            Log.d(TAG, "Values Got " + values[0].getName());
        }

        @Override
        protected void onPostExecute(Boolean aBoolean){
           super.onPostExecute(aBoolean);
        }

    }
}

