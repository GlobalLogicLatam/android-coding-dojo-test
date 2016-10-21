package com.globallogic.codingdojo.view;

import com.globallogic.codingdojo.domain.model.RSS;

/**
 * Created by david.sinner on 29/03/2016.
 */
public interface FeedsView {
    void clearFeeds();

    void displayFeeds(RSS rss);

    void displayNoAvailableDataScreen();

    void displayErrorScreen();

    void showProgress();

    void hideProgress();
}