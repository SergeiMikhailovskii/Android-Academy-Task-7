package com.mikhailovskii.exercise7.ui.page;

import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.ui.base.MvpPresenter;
import com.mikhailovskii.exercise7.ui.base.MvpView;

import java.util.List;

public interface PageContract {

    interface PageView extends MvpView {

        void onImageLoaded(String url, String title, String description, String id);

        void onImageLoadingFailed();

        void onPreviewLoaded(List<Photo> photos);

        void onPreviewLoadingFailed();

    }

    interface PagePresenter extends MvpPresenter<PageView> {

        void loadCollection(int i);

        void loadPreviewPhotos(String id);

    }

}
