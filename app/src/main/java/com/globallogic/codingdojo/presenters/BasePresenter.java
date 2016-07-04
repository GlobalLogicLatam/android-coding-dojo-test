package com.globallogic.codingdojo.presenters;

/**
 * @author julio.kun
 * @since 0.3
 */
public abstract class BasePresenter<T> {

    private T view;

    protected T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }

    public abstract void onResume();

    public abstract void onPause();

}
