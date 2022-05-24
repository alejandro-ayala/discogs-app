package com.example.myapplication;

public class DiscogsViewModel {
    private String artistName;
    private String title;
    private String country;
    private String year;
    private String genre;
    private String format;
    private String label;

    public DiscogsViewModel(String artistName){
        this.artistName = artistName;
    }
    public String getArtistName() {
        return this.artistName;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
}
