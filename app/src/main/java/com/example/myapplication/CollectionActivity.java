package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.memoryManager.FavouriteMusicEntity;
import com.example.myapplication.memoryManager.FavouriteMusicViewModel;

import java.util.List;

public class CollectionActivity extends AppCompatActivity
{
    public static final String TAG = "CollectionActivity";
    public static final String MUSIC_COLLECTION = FavouriteMusicEntity.TABLA + ".db";

    FavouriteMusicViewModel favouriteMusicViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collection_activity);


        /*final RecyclerView recyclerView = findViewById(R.id.recyclerViewFavourite);
        final MyAdapterRecycledView myAdapterRecycledView = new MyAdapterRecycledView(this);
        recyclerView.setAdapter(myAdapterRecycledView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));*/

        Intent intent = getIntent();

        FavouriteMusicViewModel favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);



        Log.d(TAG, "Collection music: ");


        favouriteMusicViewModel.getAll().observe(this, new Observer<List<FavouriteMusicEntity>>() {
            @Override
            public void onChanged(@Nullable final List<FavouriteMusicEntity> musicCollection) {
                // Update the cached copy
                LiveData<List<FavouriteMusicEntity>> favouriteMusic = favouriteMusicViewModel.getAll();
                Log.d(TAG, "Collection music: " + musicCollection.toString());
            }
        });
    }
}
