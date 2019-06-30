package com.mikhailovskii.exercise7.ui.base;

import io.reactivex.disposables.CompositeDisposable;

public class BasePresenter<View> implements MvpPresenter<View> {

    protected View view;
    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
        compositeDisposable.clear();
    }

}
