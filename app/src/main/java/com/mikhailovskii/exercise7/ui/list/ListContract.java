package com.mikhailovskii.exercise7.ui.list;

import com.mikhailovskii.exercise7.ui.base.MvpPresenter;
import com.mikhailovskii.exercise7.ui.base.MvpView;

public interface ListContract {

    interface ListView extends MvpView{

        void onPhotoesLoaded();

        void onPhotoesLoadingFailed();

    }

    interface ListPresenter extends MvpPresenter<ListView>{

        void loadPhotoList();

    }

}
