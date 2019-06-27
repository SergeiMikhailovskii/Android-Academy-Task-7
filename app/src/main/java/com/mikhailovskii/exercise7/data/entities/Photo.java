package com.mikhailovskii.exercise7.data.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Photo {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("urls")
    public Urls urls;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

}