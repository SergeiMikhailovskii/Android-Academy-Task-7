package com.mikhailovskii.exercise7.ui.page;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PagePresenter extends BasePresenter<PageContract.PageView> implements PageContract.PagePresenter {

    @Override
    public void loadCollection(int i) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhotos()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(
                        (
                                result -> view.onImageLoaded(result.get(i).getPhotos().get(0).urls.getRegular(),
                                        result.get(i).getTitle(),
                                        result.get(i).getDescription(),
                                        result.get(i).getId())
                        ),
                        (
                                throwable -> view.onImageLoadingFailed()
                        )
                )
        );
    }

    @Override
    public void loadPreviewPhotos(String id) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getCollection(id)
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
