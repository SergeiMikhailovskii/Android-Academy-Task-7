package com.mikhailovskii.exercise7.data.api;

import com.mikhailovskii.exercise7.data.entities.PhotoCollection;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface PhotosAPI {

    @GET("/collections/featured")
    Observable<List<PhotoCollection>> getPhoto();

}