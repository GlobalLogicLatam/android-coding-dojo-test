package com.globallogic.codingdojo.ui.fragments;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.domain.model.RSS;
import com.globallogic.codingdojo.ui.CodingDojoApplication;
import com.globallogic.codingdojo.ui.decorations.DividerItemDecoration;
import com.globallogic.codingdojo.ui.adapters.IItemClick;
import com.globallogic.codingdojo.R;
import com.globallogic.codingdojo.di.component.DaggerFeedComponent;
import com.globallogic.codingdojo.di.module.ActivityModule;
import com.globallogic.codingdojo.di.module.FeedModule;
import com.globallogic.codingdojo.presenters.FeedsPresenter;
import com.globallogic.codingdojo.ui.adapters.RssAdapter;
import com.globallogic.codingdojo.view.FeedsView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by david.sinner on 29/03/2016.
 */
public class FeedsFragment extends Fragment implements RssAdapter.OnItemClickListener, FeedsView {
    private static final String TAG = FeedsFragment.class.getSimpleName();
    @Inject
    protected FeedsPresenter feedsPresenter;
    @Bind(R.id.recycle_view_rss)
    RecyclerView mRssRecyclerView;
    @Bind(R.id.recycle_view_progress)
    View vProgress;
    @Bind(R.id.rl_no_data_screen_container)
    RelativeLayout vNoDataScreenRelativeLayout;
    @Bind(R.id.rl_error_screen_container)
    RelativeLayout vErrorDataScreenRelativeLayout;
    @Bind(R.id.rl_normal_screen_container)
    RelativeLayout vNomalScreenRelativeLayout;
    private LinearLayoutManager mLayoutManager;
    private RssAdapter mAdapter;
    private IItemClick mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recycle_view, container, false);
        ButterKnife.bind(this, view);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        mAdapter = new RssAdapter();
        mAdapter.setOnItemClickListener(this);

        mRssRecyclerView.setLayoutManager(mLayoutManager);
        mRssRecyclerView.setAdapter(mAdapter);

        mRssRecyclerView.setHasFixedSize(true);
        mRssRecyclerView.addItemDecoration(new DividerItemDecoration(1, Color.BLACK));
        mRssRecyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        feedsPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        feedsPresenter.onPause();
    }

    @Override
    public void onItemClickListener(Item item) {
        mCallback.openItem(item);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mCallback = (IItemClick) activity;

        DaggerFeedComponent.builder()
                .applicationComponent(((CodingDojoApplication) activity.getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(activity))
                .feedModule(new FeedModule())
                .build().inject(this);
        feedsPresenter.setView(this);
    }

    @Override
    public void displayFeeds(RSS rss) {
        mAdapter.setList(rss.getItems());
        vNomalScreenRelativeLayout.setVisibility(View.VISIBLE);
        vNoDataScreenRelativeLayout.setVisibility(View.GONE);
        vErrorDataScreenRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void clearFeeds() {
        mAdapter.clearList();
    }

    @Override
    public void displayNoAvailableDataScreen() {
        vNoDataScreenRelativeLayout.setVisibility(View.VISIBLE);
        vNomalScreenRelativeLayout.setVisibility(View.GONE);
        vErrorDataScreenRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void displayErrorScreen() {
        vErrorDataScreenRelativeLayout.setVisibility(View.VISIBLE);
        vNoDataScreenRelativeLayout.setVisibility(View.GONE);
        vNomalScreenRelativeLayout.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        vProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        vProgress.setVisibility(View.GONE);
    }

    public void searchFeed(String textToSearch) {
        feedsPresenter.search(textToSearch);
    }
}