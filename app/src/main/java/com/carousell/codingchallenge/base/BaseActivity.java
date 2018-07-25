package com.carousell.codingchallenge.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<P extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View<P> {

    @NonNull
    protected abstract P presenter();

    @LayoutRes
    protected abstract int layoutRes();

    protected abstract void bindViews();

    protected abstract void initPresenter();

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        initPresenter();
        bindViews();
        bindPresenter(presenter());
    }

    @Override
    public void bindPresenter(@NonNull P presenter) {
        presenter.attachView(this);
    }

    @Override
    public void unbindPresenter() {
        presenter().detachView();
    }
}
