package com.example.discogsMusicCollection.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
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

        // This callback will only be called when MyFragment is at least Started.
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                closeApp();
            }
        };
        this.getOnBackPressedDispatcher().addCallback(this, callback);
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

    public void closeApp() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Do you want close the APP");
        alertDialogBuilder.setTitle("Close application");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Intent intent = new Intent(MainActivity.this, FirebaseLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("EXIT", true);
                startActivity(intent);
                finish();
                System.exit(0);
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}