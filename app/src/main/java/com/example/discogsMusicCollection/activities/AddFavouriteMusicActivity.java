package com.example.discogsMusicCollection.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discogsMusicCollection.R;

public class AddFavouriteMusicActivity extends AppCompatActivity  {

    static String LOG_TAG = "AddFavouriteMusicActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);

        Intent myIntent = getIntent();

        String title = myIntent.getStringExtra("title");
        String cover= myIntent.getStringExtra("cover");

    }
}
