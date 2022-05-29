package com.example.myapplication;

public class DiscogsSearchParameter {
    private final String artistName;
    private String title;
    private String format;
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

    public String getArtistName() {
        return this.artistName;
    }
    public String getTitle() {
        return this.title;
    }
    public String getFormat() {
        return this.format;
    }
    public String getPage() {
        return this.page;
    }
    public String getPerPage() {
        return this.per_page;
    }
}
