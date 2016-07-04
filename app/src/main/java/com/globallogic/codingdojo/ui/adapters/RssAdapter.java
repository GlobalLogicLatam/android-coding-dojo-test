package com.globallogic.codingdojo.ui.adapters;

import android.support.v7.widget.RecyclerView;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by david.sinner on 29/03/2016.
 */
public class RssAdapter extends RecyclerView.Adapter<RssAdapter.RSSHolder> {
    private final List<Item> mList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    @Override
    public RSSHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rss, parent, false);
        return new RSSHolder(v);
    }

    @Override
    public void onBindViewHolder(RSSHolder holder, int position) {
        Item item = mList.get(position);
        holder.load(item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setList(List<Item> items) {
        mList.clear();
        mList.addAll(items);
        this.notifyDataSetChanged();
    }

    public void clearList() {
        mList.clear();
        this.notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(Item item);
    }

    public class RSSHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.item_rss_title)
        public TextView vTitle;
        @Bind(R.id.item_rss_description)
        public TextView vDescription;
        @Bind(R.id.item_rss_pub_date)
        public TextView vPubDate;
        @Bind(R.id.item_rss_image)
        public ImageView vImage;
        @Bind(R.id.item_rss_author)
        public TextView vAuthor;
        private Item mItem;

        public RSSHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onItemClickListener(mItem);
        }

        public void load(Item item) {
            this.mItem = item;

            vTitle.setText(item.title);
            vDescription.setText(Html.fromHtml(item.getDescription()));
            vPubDate.setText(item.getPubDate());
            if (item.content != null && !TextUtils.isEmpty(item.content.url)) {
                Picasso.with(vTitle.getContext())
                        .load(item.content.url)
                        .into(vImage);
                vImage.setContentDescription(item.content.title);
                vImage.setVisibility(View.VISIBLE);
            } else {
                vImage.setVisibility(View.GONE);
            }
            vAuthor.setText(vAuthor.getResources().getString(R.string.created_by, item.getCreator()));
        }
    }
}