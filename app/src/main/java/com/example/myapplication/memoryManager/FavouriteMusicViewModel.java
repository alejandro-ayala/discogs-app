package com.example.myapplication.memoryManager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteMusicViewModel extends AndroidViewModel {

    private FavouriteMusicCollection mRepository;

    private LiveData<List<FavouriteMusicEntity>> ldList;

    public FavouriteMusicViewModel(Application application) {
        super(application);
        mRepository = new FavouriteMusicCollection(application);
        ldList = mRepository.getAll();
    }

    public LiveData<List<FavouriteMusicEntity>> getAll() {
        return ldList;
    }

    public void insert(FavouriteMusicEntity item) {
        mRepository.insert(item);
    }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void delete(FavouriteMusicEntity item) {
        mRepository.delete(item);
    }

}
