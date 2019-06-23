package com.mikhailovskii.exercise7.data.entities;

import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("alt_description")
    private String altDescription;

    @SerializedName("urls")
    public Urls urls;

    public String getAltDescription() {
        return altDescription;
    }

    public void setAltDescription(String altDescription) {
        this.altDescription = altDescription;
    }

}