package com.example.myapplication.memoryManager;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = FavouriteMusicEntity.TABLA)
public class FavouriteMusicEntity {
    static public final String TABLA = "FavouriteMusic";

    @PrimaryKey(autoGenerate = true)
    protected int uid;
    protected String title;
    protected String country;
    protected String year;
    protected String cover;
    protected String label;
    protected String format;

    public FavouriteMusicEntity(String title, String country, String year, String cover, String label, String format) {
        this.title = title;
        this.country = country;
        this.year = year;
        this.cover = cover;
        this.label = label;
        this.format = format;

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

    public String getLabel() {
        return label;
    }

    public String getFormat() {
        return format;
    }

    public String getCountry() {
        return country;
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
