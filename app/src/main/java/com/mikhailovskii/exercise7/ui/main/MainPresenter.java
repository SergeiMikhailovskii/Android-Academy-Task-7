package com.mikhailovskii.exercise7.ui.main;

import com.mikhailovskii.exercise7.data.api.PhotosAPIFactory;
import com.mikhailovskii.exercise7.data.entities.PhotoCollection;
import com.mikhailovskii.exercise7.ui.base.BasePresenter;
import com.mikhailovskii.exercise7.ui.page.PageFragment;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainContract.MainView> implements MainContract.MainPresenter {

    private List<PhotoCollection> photoCollections;
    private PageFragment pageFragment;

    public MainPresenter() {
    }

    public MainPresenter(PageFragment pageFragment) {
        this.pageFragment = pageFragment;
    }

    @Override
    public void loadPhotoList() {
        compositeDisposable.add(PhotosAPIFactory.getInstance().getAPIService().getPhoto()
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(it -> view.showLoadingIndicator(true))
                .map(photoCollection -> photoCollections)
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(result -> pageFragment.onImageLoaded(photoCollections.get(0).getPhotos().get(0).urls.getRegular()))
        );
    }

    @Override
    public void getImageFromList(int page) {
        if (photoCollections == null) {
            loadPhotoList();
        } else {
            pageFragment.onImageLoaded(photoCollections.get(0).getPhotos().get(page).urls.getRegular());
        }
    }

}
