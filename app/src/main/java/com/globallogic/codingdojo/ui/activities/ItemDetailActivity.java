package com.globallogic.codingdojo.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.R;
import com.globallogic.codingdojo.ui.fragments.FeedDetailFragment;

/**
 * Activity that holds {@link FeedDetailFragment} to show the detail of a {@link Item}
 *
 * @author julio.kun
 * @since 0.1
 */
public class ItemDetailActivity extends AppCompatActivity {

    private final static String EXTRA_ITEM = "item";

    public static Intent newIntent(Item item, Context context) {
        Intent intent = new Intent(context, ItemDetailActivity.class);
        intent.putExtra(EXTRA_ITEM, item);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        getSupportActionBar().setTitle(R.string.detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Item item = (Item) getIntent().getSerializableExtra(EXTRA_ITEM);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_item_detail, FeedDetailFragment.newInstance(item))
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}