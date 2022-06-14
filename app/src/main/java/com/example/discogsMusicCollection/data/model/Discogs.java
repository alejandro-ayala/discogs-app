package com.example.discogsMusicCollection.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Discogs {

    @SerializedName("pagination")
    @Expose
    private Pagination pagination;
    @SerializedName("results")
    @Expose
    private List<Result> results = null;

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public class Community {

        @SerializedName("want")
        @Expose
        private Integer want;
        @SerializedName("have")
        @Expose
        private Integer have;

        public Integer getWant() {
            return want;
        }

        public void setWant(Integer want) {
            this.want = want;
        }

        public Integer getHave() {
            return have;
        }

        public void setHave(Integer have) {
            this.have = have;
        }

    }


    public class Pagination {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("pages")
        @Expose
        private Integer pages;
        @SerializedName("per_page")
        @Expose
        private Integer perPage;
        @SerializedName("items")
        @Expose
        private Integer items;
        @SerializedName("urls")
        @Expose
        private Urls urls;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getPages() {
            return pages;
        }

        public void setPages(Integer pages) {
            this.pages = pages;
        }

        public Integer getPerPage() {
            return perPage;
        }

        public void setPerPage(Integer perPage) {
            this.perPage = perPage;
        }

        public Integer getItems() {
            return items;
        }

        public void setItems(Integer items) {
            this.items = items;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

    }

    public class Result {

        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("year")
        @Expose
        private String year;
        @SerializedName("format")
        @Expose
        private List<String> format = null;
        @SerializedName("label")
        @Expose
        private List<String> label = null;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("genre")
        @Expose
        private List<String> genre = null;
        @SerializedName("style")
        @Expose
        private List<String> style = null;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("barcode")
        @Expose
        private List<String> barcode = null;
        @SerializedName("user_data")
        @Expose
        private UserData userData;
        @SerializedName("master_id")
        @Expose
        private Integer masterId;
        @SerializedName("master_url")
        @Expose
        private String masterUrl;
        @SerializedName("uri")
        @Expose
        private String uri;
        @SerializedName("catno")
        @Expose
        private String catno;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("thumb")
        @Expose
        private String thumb;
        @SerializedName("cover_image")
        @Expose
        private String coverImage;
        @SerializedName("resource_url")
        @Expose
        private String resourceUrl;
        @SerializedName("community")
        @Expose
        private Community community;

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public List<String> getFormat() {
            return format;
        }

        public void setFormat(List<String> format) {
            this.format = format;
        }

        public List<String> getLabel() {
            return label;
        }

        public void setLabel(List<String> label) {
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public List<String> getGenre() {
            return genre;
        }

        public void setGenre(List<String> genre) {
            this.genre = genre;
        }

        public List<String> getStyle() {
            return style;
        }

        public void setStyle(List<String> style) {
            this.style = style;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public List<String> getBarcode() {
            return barcode;
        }

        public void setBarcode(List<String> barcode) {
            this.barcode = barcode;
        }

        public UserData getUserData() {
            return userData;
        }

        public void setUserData(UserData userData) {
            this.userData = userData;
        }

        public Integer getMasterId() {
            return masterId;
        }

        public void setMasterId(Integer masterId) {
            this.masterId = masterId;
        }

        public String getMasterUrl() {
            return masterUrl;
        }

        public void setMasterUrl(String masterUrl) {
            this.masterUrl = masterUrl;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public String getCatno() {
            return catno;
        }

        public void setCatno(String catno) {
            this.catno = catno;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getCoverImage() {
            return coverImage;
        }

        public void setCoverImage(String coverImage) {
            this.coverImage = coverImage;
        }

        public String getResourceUrl() {
            return resourceUrl;
        }

        public void setResourceUrl(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }

        public Community getCommunity() {
            return community;
        }

        public void setCommunity(Community community) {
            this.community = community;
        }

    }

    public class Urls {

        @SerializedName("last")
        @Expose
        private String last;
        @SerializedName("next")
        @Expose
        private String next;

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }

    }

    public class UserData {

        @SerializedName("in_wantlist")
        @Expose
        private Boolean inWantlist;
        @SerializedName("in_collection")
        @Expose
        private Boolean inCollection;

        public Boolean getInWantlist() {
            return inWantlist;
        }

        public void setInWantlist(Boolean inWantlist) {
            this.inWantlist = inWantlist;
        }

        public Boolean getInCollection() {
            return inCollection;
        }

        public void setInCollection(Boolean inCollection) {
            this.inCollection = inCollection;
        }

    }
}
