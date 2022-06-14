package com.example.discogsMusicCollection.memoryManager;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class FavouriteMusicCollection {
    private IFavouriteMusicDAO iItemDAO;
    private LiveData<List<FavouriteMusicEntity>> ldList;

    public FavouriteMusicCollection(Application application) {
        FavouriteMusicRoomDatabase db = FavouriteMusicRoomDatabase.getDatabase(application);
        iItemDAO = db.userDAO();
        ldList = iItemDAO.getAll();
    }

    public LiveData<List<FavouriteMusicEntity>> getAll() {
        return ldList;
    }

    public long insert(FavouriteMusicEntity item) {
        return iItemDAO.insert(item);
    }

    public void deleteAll() {
        iItemDAO.deleteAll();
    }

    public void delete(FavouriteMusicEntity item)  {
        iItemDAO.delete(item);
    }

}
