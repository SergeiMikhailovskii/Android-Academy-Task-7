package com.mikhailovskii.exercise7.ui.photo_collection;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PhotoCollectionPresenter extends BasePresenter<PhotoCollectionContract.PageView> implements PhotoCollectionContract.PagePresenter {

    @Override
    public void loadPhotoCollections(int i) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhotoCollections()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .map(result -> result.get(i))
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe((result -> view.onImageLoaded(result.getPhotos().get(0).urls.getRegular(),
                        result.getTitle(),
                        result.getDescription(),
                        result.getId())),
                        (throwable -> view.onImageLoadingFailed())
                )
        );
    }

    @Override
    public void loadPreviewPhotos(String id) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhotos(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(
                        (
                                result -> view.onPreviewLoaded(result)
                        ),
                        (
                                throwable -> view.onPreviewLoadingFailed()
                        )
                )
        );
    }

}
