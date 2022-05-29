package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    public static final String ARTIST_TO_SEARCH = "artist";
    public static final String TITLE_TO_SEARCH = "title";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void searchDisc(View view) {
        // Do something in response to button click
        Log.d(TAG, "Search disc!!");

        Intent intent = new Intent(this,SearchActivity.class);

        EditText etArtist = (EditText)findViewById(R.id.etArtist);
        EditText etTitle = (EditText)findViewById(R.id.etTitle);
        String artist = etArtist.getText().toString();
        String title = etTitle.getText().toString();

        Log.d(TAG,  artist);
        Log.d(TAG,  title);
        intent.putExtra(ARTIST_TO_SEARCH,artist);
        intent.putExtra(TITLE_TO_SEARCH,title);
        startActivity(intent);

    }
}