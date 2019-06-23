package com.mikhailovskii.exercise7.ui.main;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter {

    @Override
    public void loadPhotoList() {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhoto()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(photo -> {
                            if (photo != null) {
                                view.showEmptyState(false);
                                view.onPhotosLoaded(photo.urls.getRegular(), photo.getAltDescription());
                            } else {
                                view.showEmptyState(true);
                            }
                        }, error -> view.onPhotosLoadingFailed()
                )
        );
    }

}
