package com.mikhailovskii.exercise7.ui.main;

import com.mikhailovskii.exercise7.ui.base.MvpPresenter;
import com.mikhailovskii.exercise7.ui.base.MvpView;

public interface MainContract {

    interface MainView extends MvpView {

        void onPhotosLoaded(String url, String description);

        void onPhotosLoadingFailed();

    }

    interface MainPresenter extends MvpPresenter<MainView> {

        void loadPhotoList();

        void getImageFromList(int page);

    }

}
