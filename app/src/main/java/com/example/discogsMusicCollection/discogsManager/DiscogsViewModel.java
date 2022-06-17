package com.example.discogsMusicCollection.discogsManager;

public class DiscogsViewModel {
    private String title;
    private String country;
    private String year;
    private String cover;
    private String format;
    private String label;
    private int uid;

    public DiscogsViewModel(String title, String country, String year, String label, String cover, String format){
        this.title = title;
        this.country = country;
        this.year = year;
        this.label = label;
        this.format = format;
        this.cover = cover;
    }


    public String getTitle() {return this.title;}
    public void setTitle(String title){
        this.title = title;
    }
    public String getCountry() {return this.country;}
    public String getYearRelease() {
        return this.year;
    }
    public String getCover() {
        return this.cover;
    }
    public String getLabel() {
        return this.label;
    }

    public String getFormat() {
        return this.format;
    }

    public int getUid(){return this.uid;}
    public void setUid(int uid){this.uid = uid;}

}
