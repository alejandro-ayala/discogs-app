package com.example.discogsMusicCollection.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.discogsMusicCollection.FirebaseLogin;
import com.example.discogsMusicCollection.R;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String LOGOUT_REQUEST = "logout-request";
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
        Intent intent = new Intent(this, CollectionActivity.class);
        startActivity(intent);
    }

    public void goToProfile(View view) {
        Log.d(TAG, "goToProfile!!");
        Intent intent = new Intent(this,UserProfileActivity.class);
        startActivity(intent);
    }

    public void logout(View view) {
        // Do something in response to button click
        Log.d(TAG, "logout!!");
        Intent intent = new Intent(this, FirebaseLogin.class);
        intent.putExtra(LOGOUT_REQUEST,true);
        startActivity(intent);
    }
}