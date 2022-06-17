package com.example.discogsMusicCollection.discogsManager.retrofit;

public class DiscogsSearchParameter {
    private final String artistName;
    private String title;
    private String format;
    private String year;
    private String page;
    private String per_page;

    public DiscogsSearchParameter(String artistName){
        this.artistName = artistName;
        this.page = "1";
        this.per_page = "3";
    }

    public DiscogsSearchParameter(String artistName, String title){
        this.artistName = artistName;
        this.title = title;
        this.page = "1";
        this.per_page = "3";
    }

    public DiscogsSearchParameter(String artistName, String title, String format){
        this.artistName = artistName;
        this.title = title;
        this.format = format;
        this.page = "1";
        this.per_page = "3";
    }

    public DiscogsSearchParameter(String artistName, String title, String format,String year){
        this.artistName = artistName;
        this.title = title;
        this.format = format;
        this.year = year;
        this.page = "1";
        this.per_page = "3";
    }
    public String getArtistName() {
        return this.artistName;
    }
    public String getTitle() {
        return this.title;
    }
    public String getFormat() {
        return this.format;
    }
    public String getYear() {
        return this.year;
    }
    public String getPage() {
        return this.page;
    }
    public String getPerPage() {
        return this.per_page;
    }
}
