package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.memoryManager.FavouriteMusicEntity;
import com.example.myapplication.memoryManager.FavouriteMusicViewModel;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity
{
    public static final String TAG = "CollectionActivity";
    public static final String MUSIC_COLLECTION = FavouriteMusicEntity.TABLA + ".db";
    List<DiscogsViewModel> favouriteMusicList = new ArrayList<DiscogsViewModel>();

    FavouriteMusicViewModel favouriteMusicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);

        Intent intent = getIntent();

        FavouriteMusicViewModel favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);

        // Observer to async read the database
        favouriteMusicViewModel.getAll().observe(this, musicCollection -> {

            Log.d(TAG, "Reading Collection music");

            for (FavouriteMusicEntity favouriteRelease : musicCollection) {

                DiscogsViewModel newItem = new DiscogsViewModel(favouriteRelease.getTitle(),"", favouriteRelease.getYear(),favouriteRelease.getCover());
                favouriteMusicList.add(newItem);

            }
                final RecyclerView recyclerView = findViewById(R.id.recyclerViewFavourite);
                recyclerView.setLayoutManager(new GridLayoutManager(this,2));
                final adapterRecyclerViewFavourite favouriteRecycledView = new adapterRecyclerViewFavourite(this,favouriteMusicList);
            adapterRecyclerViewFavourite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position = recyclerView.getChildLayoutPosition(view);
                    Log.d(TAG, "onClick --> Save release to collection: " + position);
                }
            });
                recyclerView.setAdapter(favouriteRecycledView);


        });
    }
}
