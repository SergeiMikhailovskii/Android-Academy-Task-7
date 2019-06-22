package com.mikhailovskii.exercise7.ui.base;

public class BasePresenter<View> implements MvpPresenter<View> {

    private View view;

    @Override
    public void attachView(View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

}
