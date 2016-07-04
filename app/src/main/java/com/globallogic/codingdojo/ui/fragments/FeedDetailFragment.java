package com.globallogic.codingdojo.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.domain.model.Item;
import com.globallogic.codingdojo.R;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Fragment that display the detail of a {@link com.domain.model.Item}
 *
 * @author julio.kun
 * @since 0.1
 */
public class FeedDetailFragment extends Fragment {

    private static final String EXTRA_ITEM = "item";
    @Bind(R.id.tv_item_rss_title)
    protected TextView vFeedTitle;
    @Bind(R.id.iv_item_rss_image)
    protected ImageView vFeedImage;
    @Bind(R.id.tv_item_rss_description)
    protected TextView vFeedDescription;
    @Bind(R.id.tv_item_rss_pub_date)
    protected TextView vFeedDate;

    public static FeedDetailFragment newInstance(Item item) {
        FeedDetailFragment fragment = new FeedDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putSerializable(EXTRA_ITEM, item);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_detail, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        showFeedInformaion();
    }

    private void showFeedInformaion() {
        Item item = (Item) getArguments().getSerializable(EXTRA_ITEM);
        if (item != null) {
            vFeedTitle.setText(item.title);
            vFeedDescription.setText(Html.fromHtml(item.fullContent));
            vFeedDate.setText(item.pubDate);

            if (item.content != null && !TextUtils.isEmpty(item.content.url)) {
                Picasso.with(vFeedTitle.getContext())
                        .load(item.content.url)
                        .into(vFeedImage);
            }
        }
    }
}