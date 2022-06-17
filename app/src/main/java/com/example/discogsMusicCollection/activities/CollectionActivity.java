package com.example.discogsMusicCollection.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.discogsMusicCollection.R;
import com.example.discogsMusicCollection.userInterface.adapterRecyclerViewFavourite;
import com.example.discogsMusicCollection.memoryManager.FavouriteMusicEntity;
import com.example.discogsMusicCollection.memoryManager.FavouriteMusicViewModel;

public class CollectionActivity extends AppCompatActivity
{
    public static final String TAG = "CollectionActivity";
    public static final String MUSIC_COLLECTION = FavouriteMusicEntity.TABLA + ".db";
    adapterRecyclerViewFavourite favouriteRecycledView;

    int position = 0;
    FavouriteMusicViewModel favouriteMusicViewModel;
    PopupWindow popupWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);

        Intent intent = getIntent();

        favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);

        // Observer to async read the database
        favouriteMusicViewModel.getAll().observe(this, musicCollection -> {

            Log.d(TAG, "Reading Collection music");

                final RecyclerView recyclerView = findViewById(R.id.recyclerViewFavourite);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                favouriteRecycledView = new adapterRecyclerViewFavourite(this,musicCollection);
            adapterRecyclerViewFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String title = musicCollection.get(position).getTitle();
                    String year = musicCollection.get(position).getYear();
                    String format = musicCollection.get(position).getFormat();
                    String label = musicCollection.get(position).getLabel();
                    String country = musicCollection.get(position).getCountry();
                    String releaseInfo = "Year: " + year + "\n Format: " + format + "\n Label: " + label + "\nCountry: " + country;

                    showFavouriteReleaseInformation(title,releaseInfo);
                }
            });
                recyclerView.setAdapter(favouriteRecycledView);


        });
    }

    public void showFavouriteReleaseInformation(String title, String releaseInfo){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(releaseInfo);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setPositiveButton("ok", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(CollectionActivity.this,CollectionActivity.class);
                startActivity(intent);
            }
        });

        alertDialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d(TAG, "onClick --> Delete release");
                FavouriteMusicEntity itemToDelete = favouriteRecycledView.getItemByPosition(position);
                favouriteMusicViewModel.delete(itemToDelete);
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
