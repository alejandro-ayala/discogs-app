package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class UserProfileActivity extends AppCompatActivity {

    static final String TAG = "UserProfileActivity";

    MyAdapterRecycledView myAdapterRecycledView;

    private RecyclerView.LayoutManager layoutManager;

    RecyclerView recyclerView;
    Controller controller = new Controller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userprofile_activity);

        Intent intent = getIntent();

    }

    public void saveProfile(View view) {
        // Do something in response to button click
        Log.d(TAG, "saveProfile!!");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
