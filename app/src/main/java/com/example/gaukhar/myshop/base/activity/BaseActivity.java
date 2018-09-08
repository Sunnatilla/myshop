package com.example.gaukhar.myshop.base.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.example.gaukhar.myshop.R;
import com.example.gaukhar.myshop.manager.mvp.base.BaseMvpView;

import butterknife.BindView;

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseMvpView {

    @BindView(R.id.progress_bar)
    ContentLoadingProgressBar progressBar;

    public abstract int getLayoutResource();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }

    @Override
    public void showLoadingIndicator(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showErrorMsg() {

    }
}
