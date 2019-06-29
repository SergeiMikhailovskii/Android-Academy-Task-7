package com.mikhailovskii.exercise7.data.api;

import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.data.entities.PhotoCollection;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotosAPI {

    @GET("/collections/featured")
    Observable<List<PhotoCollection>> getPhotos();

    @GET("/photos/{id}")
    Observable<Photo> getPhoto(@Path("id") String id);

    @GET("/collections/{id}/photos")
    Observable<PhotoCollection> getCollection(@Path("id") String id);

}