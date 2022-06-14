package com.example.discogsMusicCollection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class UserProfileActivity extends AppCompatActivity {

    static final String TAG = "UserProfileActivity";
    public static final String LOGOUT_REQUEST = "logout-request";

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
    public void logout(View view) {
        // Do something in response to button click
        Log.d(TAG, "logout!!");
        Intent intent = new Intent(this,FirebaseLogin.class);
        intent.putExtra(LOGOUT_REQUEST,true);
        startActivity(intent);

    }

    public void saveProfile(View view) {
        // Do something in response to button click
        Log.d(TAG, "saveProfile!!");
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}
