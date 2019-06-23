package com.mikhailovskii.exercise7.data.api;

import com.mikhailovskii.exercise7.data.entities.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PhotosAPI {

    @GET("/photos/random")
    Observable<Photo> getPhoto();

}