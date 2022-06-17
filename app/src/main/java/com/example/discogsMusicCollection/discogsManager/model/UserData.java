package com.example.discogsMusicCollection.discogsManager.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
