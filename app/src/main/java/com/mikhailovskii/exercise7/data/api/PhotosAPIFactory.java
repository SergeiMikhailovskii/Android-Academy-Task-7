package com.mikhailovskii.exercise7.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosAPIFactory {

    private static PhotosAPIFactory mInstance;
    private Retrofit mRetrofit;

    private PhotosAPIFactory() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(chain -> {
            Request original = chain.request();
            Request requestBuilder = original.newBuilder()
                    .header("Authorization", "Client-ID 6ffda649e9e02face25fab40d54be28f529c40de8d509459b3aa3304ec3f6146")
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(requestBuilder);
        });

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com")
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static PhotosAPIFactory getInstance() {
        if (mInstance == null) {
            mInstance = new PhotosAPIFactory();
        }
        return mInstance;
    }

    public PhotosAPI getAPIService() {
        return mRetrofit.create(PhotosAPI.class);
    }

}
