package com.mikhailovskii.exercise7.ui.preview;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PreviewPresenter extends BasePresenter<PreviewContract.PreviewView> implements PreviewContract.PreviewPresenter {

    @Override
    public void loadPreviewList(String id) {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getCollection(id)
                .observeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(
                        (
                                result -> view.onPreviewListLoaded(result.getPhotos())
                        ),
                        (
                                throwable -> view.onPreviewListLoadingFailed()
                        )
                ));
    }

}
