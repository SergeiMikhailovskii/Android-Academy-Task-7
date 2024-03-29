package com.mikhailovskii.exercise7.data.api;

import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.data.entities.PhotoCollection;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotosAPI {

    @GET("/collections/featured")
    Observable<List<PhotoCollection>> getPhotoCollections();

    @GET("/collections/{id}/photos")
    Observable<List<Photo>> getPhotos(@Path("id") String id);

}