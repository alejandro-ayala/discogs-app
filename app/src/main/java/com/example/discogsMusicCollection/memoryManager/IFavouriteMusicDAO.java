package com.example.discogsMusicCollection.memoryManager;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;


@Dao
public interface IFavouriteMusicDAO {
    @Query("SELECT * FROM " + FavouriteMusicEntity.TABLA)
    LiveData<List<FavouriteMusicEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(FavouriteMusicEntity group);

    @Query("DELETE FROM " + FavouriteMusicEntity.TABLA)
    void deleteAll();

    @Delete
    void delete(FavouriteMusicEntity group);

}
