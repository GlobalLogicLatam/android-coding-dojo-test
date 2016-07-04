package com.domain.interactors;


import com.domain.callback.Callback;
import com.domain.mappers.Transformable;
import com.domain.model.Item;
import com.domain.model.RSS;
import com.domain.utils.Checker;
import com.domain.utils.CheckerUtil;
import com.globallogic.data.dto.RssDTO;
import com.globallogic.data.repository.FeedsRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * @author julio.kun
 * @since 0.1
 */
public class GetFeedsUseCase {

    private final FeedsRepository repository;
    private final Transformable<RssDTO, RSS> mapper;
    private final ArrayList<Item> items;
    private Callback callback;
    private RSS rss;

    @Inject
    public GetFeedsUseCase(FeedsRepository repository, Transformable<RssDTO, RSS> mapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.items = new ArrayList<>();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void getFeeds() {
        if (rss == null) {
            repository.getFeeds(new retrofit2.Callback<RssDTO>() {
                @Override
                public void onResponse(Call<RssDTO> call, Response<RssDTO> response) {
                    RSS rss = mapper.transform(response.body());
                    cacheResponse(rss);
                    if (callback != null) {
                        callback.onSuccess(rss);
                        callback.onFinish();
                    }
                }

                @Override
                public void onFailure(Call<RssDTO> call, Throwable t) {
                    if (callback != null) {
                        callback.onError(t);
                        callback.onFinish();
                    }
                }
            });
        } else {
            rss.getItems().clear();
            rss.getItems().addAll(items);
            if (callback != null) {
                callback.onSuccess(rss);
                callback.onFinish();
            }
        }
    }

    public void searchFeeds(String searchText) {
        if (searchText == null) {
            if (callback != null) {
                callback.onError(new Exception("text null"));
                callback.onFinish();
            }
            return;
        }

        searchText = searchText.trim();
        if (searchText.length() < 3) {
            getFeeds();
        } else {
            if (rss == null) {
                getFeeds();
            } else {
                if (callback != null) {
                    callback.onSuccess(getRSSFiltered(searchText));
                    callback.onFinish();
                }
            }
        }
    }

    private void cacheResponse(RSS rss) {
        this.rss = rss;
        this.items.clear();
        this.items.addAll(rss.getItems());
    }

    private RSS getRSSFiltered(String searchText) {
        rss.getItems().clear();
        rss.getItems().addAll(CheckerUtil.findAll(items, new ItemChecker(searchText)));
        return this.rss;
    }

    private class ItemChecker implements Checker<Item> {
        private final String textToSearch;

        public ItemChecker(String textToSearch) {
            this.textToSearch = textToSearch;
        }

        @Override
        public boolean check(Item obj) {
            return obj.description.contains(textToSearch);
        }
    }
}