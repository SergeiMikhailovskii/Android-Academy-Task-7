package com.mikhailovskii.exercise7.ui.preview;

import com.mikhailovskii.exercise7.data.entities.Photo;
import com.mikhailovskii.exercise7.ui.base.MvpPresenter;
import com.mikhailovskii.exercise7.ui.base.MvpView;

import java.util.List;

public interface PreviewContract {

    interface PreviewView extends MvpView {

        void onPreviewListLoaded(List<Photo> photos);

        void onPreviewListLoadingFailed();

    }

    interface PreviewPresenter extends MvpPresenter<PreviewView> {

        void loadPreviewList(String id);

    }

}
