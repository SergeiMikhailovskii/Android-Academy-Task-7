package com.mikhailovskii.exercise7.ui.page;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PagePresenter extends BasePresenter<PageContract.PageView> implements PageContract.PagePresenter {

    @Override
    public void loadImage(int i) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhotos()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(result -> view.onImageLoaded(result.get(0).getPhotos().get(i).urls.getRegular(), result.get(0).getPhotos().get(i).getId()))
        );
    }

    @Override
    public void loadDescription(String id) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhoto(id)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(result -> view.onDescriptionLoaded(result.getDescription()))
        );
    }


}
