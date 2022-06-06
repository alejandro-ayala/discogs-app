package com.example.myapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.memoryManager.FavouriteMusicEntity;
import com.example.myapplication.memoryManager.FavouriteMusicViewModel;

import java.util.List;

public class MemoryManagerActivity extends AppCompatActivity {
    private static final String TAG = "Controller";
    FavouriteMusicViewModel mMusicCollection;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FavouriteMusicViewModel favouriteMusicViewModel;
        favouriteMusicViewModel = ViewModelProviders.of(this).get(FavouriteMusicViewModel.class);
        FavouriteMusicEntity music = new FavouriteMusicEntity("sdssd","sdasd");
        favouriteMusicViewModel.insert(music);

    }
    public void saveFavouriteRelease(String title, String urlCover) {


        FavouriteMusicEntity music = new FavouriteMusicEntity("nirvana","sdasd");
        mMusicCollection.insert(music);
        LiveData<List<FavouriteMusicEntity>> musicCollection = mMusicCollection.getAll();
    }
}
