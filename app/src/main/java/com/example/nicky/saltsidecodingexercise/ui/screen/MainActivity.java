package com.example.nicky.saltsidecodingexercise.ui.screen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.nicky.saltsidecodingexercise.R;
import com.example.nicky.saltsidecodingexercise.data.Items;
import com.example.nicky.saltsidecodingexercise.ui.base.BaseLifecycleActivity;

public class MainActivity extends AppCompatActivity implements ItemListAdapter.OnItemClickListener {

    public static final String SELECTED_ITEM = "SELECTED_ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            ItemListFragment fragment = new ItemListFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, fragment, ItemListFragment.TAG).commit();
        }
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onItemClick(Items item) {
        Bundle data = new Bundle();
        data.putParcelable(SELECTED_ITEM, item);
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(data);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        fragment, null).commit();

    }
}
