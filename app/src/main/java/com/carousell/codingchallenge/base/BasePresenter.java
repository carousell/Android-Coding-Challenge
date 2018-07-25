package com.carousell.codingchallenge.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class BasePresenter<V> implements BaseContract.Presenter<V> {

    @Nullable
    private V view;

    @Override
    @CallSuper
    public void attachView(@NonNull final V view) {
        this.view = view;
        updateView();
    }

    @Override
    @CallSuper
    public void detachView() {
        view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    @Nullable
    public V getView() {
        return view;
    }

    protected void updateView() {

    }
}
