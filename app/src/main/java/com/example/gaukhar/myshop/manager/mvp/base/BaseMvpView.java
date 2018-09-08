package com.example.gaukhar.myshop.manager.mvp.base;

import com.arellomobile.mvp.MvpView;

public interface BaseMvpView extends MvpView {

    void showLoadingIndicator(boolean isShow);

    void showErrorMsg();
}
