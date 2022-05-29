package com.example.myapplication;

public class DiscogsViewModel {
    private String artistName;
    private String title;
    private String country;
    private String year;
    private String cover;
    private String format;
    private String label;

    public DiscogsViewModel(String title, String country, String year, String cover){
        this.title = title;
        this.country = country;
        this.year = year;
        this.cover = cover;
    }


    public String getTitle() {return this.title;}
    public void setTitle(String title){
        this.title = title;
    }

    public String getCountry() {return this.country;}
    public void setCountry(String country){
        this.country = country;
    }

    public String getYearRelease() {
        return this.year;
    }
    public void setYearRelease(String year){
        this.year = year;
    }

    public String getCover() {
        return this.cover;
    }
    public void setCover(String cover){
        this.cover = cover;
    }
}
