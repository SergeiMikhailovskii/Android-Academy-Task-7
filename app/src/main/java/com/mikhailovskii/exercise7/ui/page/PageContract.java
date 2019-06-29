package com.mikhailovskii.exercise7.ui.page;

import com.mikhailovskii.exercise7.ui.base.MvpPresenter;
import com.mikhailovskii.exercise7.ui.base.MvpView;

public interface PageContract {

    interface PageView extends MvpView {

        void onImageLoaded(String url, String title, String description, String id);

        void onImageLoadingFailed();

        void onDescriptionLoaded(String description);

        void onDescriptionLoginFailed();

    }

    interface PagePresenter extends MvpPresenter<PageView> {

        void loadCollection(int i);

        void loadDescription(String id);

    }

}