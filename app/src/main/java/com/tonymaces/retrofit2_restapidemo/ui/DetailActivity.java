package com.tonymaces.retrofit2_restapidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tonymaces.retrofit2_restapidemo.R;
import com.tonymaces.retrofit2_restapidemo.model.Flower;
import com.tonymaces.retrofit2_restapidemo.model.helper.Constants;

/**
 * Created by tonym on 30/06/2016.
 */
public class DetailActivity extends AppCompatActivity {
    private TextView mName, mId, mCategory, mInstruction, mPrice;
    private ImageView mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        Flower flower = (Flower) intent.getSerializableExtra(Constants.REFERENCE.FLOWER);
        configViews();

        mId.setText(" " + flower.getProductId());
        mName.setText(flower.getName());
        mCategory.setText(flower.getCategory());
        mInstruction.setText(flower.getInstructions());
        mPrice.setText("$ " + Double.toString(flower.getPrice()));
        Picasso.with(getApplicationContext()).load("http://services.hanselandpetal.com/photos/" + flower.getPhoto()).into(mPhoto);
    }

    private void configViews() {
        mPhoto = (ImageView) findViewById(R.id.flowerPhoto);
        mName = (TextView) findViewById(R.id.flowerName);
        mId = (TextView) findViewById(R.id.flowerId);
        mCategory = (TextView) findViewById(R.id.flowerCategory);
        mInstruction = (TextView) findViewById(R.id.flowerInstructions);
        mPrice = (TextView) findViewById(R.id.flowerPrice);
    }
}
