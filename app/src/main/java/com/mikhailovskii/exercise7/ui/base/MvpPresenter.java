package com.mikhailovskii.exercise7.ui.base;

public interface MvpPresenter<View> {

    void attachView(View view);

    void detachView();

}