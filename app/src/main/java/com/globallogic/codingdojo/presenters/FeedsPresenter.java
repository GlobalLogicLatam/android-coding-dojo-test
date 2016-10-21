package com.globallogic.codingdojo.presenters;

import com.globallogic.codingdojo.domain.callback.Callback;
import com.globallogic.codingdojo.domain.interactors.GetFeedsUseCase;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.view.FeedsView;

import javax.inject.Inject;

/**
 * Presenter in charge of formatting the data to be displayed in the {@link FeedsView}
 *
 * @author julio.kun
 * @since 0.1
 */
public class FeedsPresenter extends BasePresenter<FeedsView> implements Callback {

    private final GetFeedsUseCase useCase;
    private RSS rss;

    @Inject
    public FeedsPresenter(GetFeedsUseCase getFeedsUseCase) {
        useCase = getFeedsUseCase;
        useCase.setCallback(this);
    }

    private void getFeeds() {
        useCase.getFeeds();
    }

    @Override
    public void onResume() {
        if (rss == null) {
            getView().showProgress();
            getFeeds();
        } else {
            getView().displayFeeds(rss);
        }
    }

    @Override
    public void onPause() {
        //do nothing
    }

    public void search(String searchText) {
        getView().showProgress();
        getView().clearFeeds();
        useCase.searchFeeds(searchText);
    }

    @Override
    public void onError(Throwable t) {
        getView().displayErrorScreen();
    }

    @Override
    public void onSuccess(RSS rss) {
        this.rss = rss;
        if (rss.getItems() == null || rss.getItems().size() == 0) {
            getView().displayNoAvailableDataScreen();
        } else {
            getView().displayFeeds(rss);
        }
    }

    @Override
    public void onFinish() {
        getView().hideProgress();
    }
}
