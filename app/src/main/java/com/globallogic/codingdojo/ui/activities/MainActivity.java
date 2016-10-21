package com.globallogic.codingdojo.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;

import com.globallogic.codingdojo.ui.adapters.IItemClick;
import com.globallogic.codingdojo.R;
import com.globallogic.codingdojo.domain.model.Item;
import com.globallogic.codingdojo.ui.fragments.FeedsFragment;

public class MainActivity extends AppCompatActivity implements IItemClick {
    private final SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            FeedsFragment fr = (FeedsFragment) getSupportFragmentManager().findFragmentById(R.id.activity_main_container);
            if (fr != null) {
                fr.searchFeed(newText);
                return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.activity_main_container, new FeedsFragment())
                .commit();
    }

    @Override
    public void openItem(Item item) {
        startActivity(ItemDetailActivity.newIntent(item, this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onCreateOptionsMenu(menu);
    }
}