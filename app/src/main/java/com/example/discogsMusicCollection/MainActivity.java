package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Intent intent = getIntent();
    }

    public void goToSearch(View view) {
        Log.d(TAG, "goToSearch!!");
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void goToCollection(View view) {
        Log.d(TAG, "goToCollection!!");
        Intent intent = new Intent(this,CollectionActivity.class);
        startActivity(intent);
    }
}