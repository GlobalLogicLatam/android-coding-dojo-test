package com.globallogic.codingdojo.mock;

import com.domain.model.RSS;
import com.globallogic.codingdojo.view.FeedsView;

/**
 * @author julio.kun
 * @since 0.1
 * <p>
 * <p/>
 * </p>
 */
public class TestFeedListView implements FeedsView {

    private RSS rss;
    private boolean showNoAvailableDataScreen;
    private boolean showErrorScreen;
    private boolean showListScreen;
    private boolean showProgress;

    @Override
    public void displayFeeds(RSS rss) {
        this.rss = rss;
        showNoAvailableDataScreen = false;
        showErrorScreen = false;
        showListScreen = true;
    }

    @Override
    public void displayNoAvailableDataScreen() {
        showNoAvailableDataScreen = true;
        showErrorScreen = false;
        showListScreen = false;
    }

    @Override
    public void displayErrorScreen() {
        showNoAvailableDataScreen = false;
        showErrorScreen = true;
        showListScreen = false;
    }

    @Override
    public void showProgress() {
        showProgress = true;
    }

    @Override
    public void hideProgress() {
        showProgress = false;
    }

    public RSS getRss() {
        return rss;
    }

    public boolean isShowNoAvailableDataScreen() {
        return showNoAvailableDataScreen;
    }

    public boolean isShowErrorScreen() {
        return showErrorScreen;
    }

    public boolean isShowListScreen() {
        return showListScreen;
    }

    public boolean isShowProgress() {
        return showProgress;
    }

    @Override
    public void clearFeeds() {
        rss = null;
    }
}
