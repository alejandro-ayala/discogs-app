package com.example.myapplication.memoryManager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FavouriteMusicEntity.TABLA)
public class FavouriteMusicEntity {
    static public final String TABLA = "FavouriteMusic";

    @PrimaryKey(autoGenerate = true)
    protected int uid;
    protected String title;
    protected String year;
    protected String cover;


    public FavouriteMusicEntity(String title, String cover) {
        this.title = title;
        this.cover = cover;
    }

    public int getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getCover() {
        return cover;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void SetCover(String cover) {
        this.cover = cover;
    }
}
