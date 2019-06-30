package com.mikhailovskii.exercise7.data.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PhotosAPIFactory {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_VALUE = "Client-ID 6ffda649e9e02face25fab40d54be28f529c40de8d509459b3aa3304ec3f6146";
    private static final String BASE_URL = "https://api.unsplash.com";

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
                    .header(AUTHORIZATION_HEADER, AUTHORIZATION_VALUE)
                    .method(original.method(), original.body())
                    .build();

            return chain.proceed(requestBuilder);
        });

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
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
