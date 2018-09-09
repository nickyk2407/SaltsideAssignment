package com.example.nicky.saltsidecodingexercise.ui.screen;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nicky.saltsidecodingexercise.R;
import com.example.nicky.saltsidecodingexercise.data.Items;
import com.example.nicky.saltsidecodingexercise.ui.viewmodel.ItemViewModel;

import java.util.ArrayList;

/**
 * Created by NICKY on 09-09-2018.
 */

public class ItemListFragment extends Fragment {

    public static final String TAG = ItemListFragment.class.getName();
    private View view;
    private ItemListAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private TextView mEmptyView;
    private ProgressBar mProgressbar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_items_list, container, false);
        ((MainActivity) getActivity()).setActionBarTitle(getString(R.string.app_name));
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mEmptyView = view.findViewById(R.id.empty);
        mProgressbar = view.findViewById(R.id.progressbar);

        setupRecyclerView();
        ItemViewModel viewModel = getViewModel();
        viewModel.getJsonDataList().observe(this, listResponse -> {
            if (listResponse != null && !listResponse.isEmpty()) {
                mEmptyView.setVisibility(View.GONE);
                mAdapter.setValues(listResponse);
            } else {
                mEmptyView.setVisibility(View.VISIBLE);
            }
        });

        viewModel.isLoading().observe(this, isLoading -> {
            if (isLoading != null && isLoading) {
                mProgressbar.setVisibility(View.VISIBLE);
            } else {
                mProgressbar.setVisibility(View.GONE);
            }
        });
        return view;
    }


    private ItemViewModel getViewModel() {
        return ViewModelProviders.of(this).get(ItemViewModel.class);
    }

    private void setupRecyclerView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new ItemListAdapter(new ArrayList<>());

        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(onItemClickListener);

    }


    private final ItemListAdapter.OnItemClickListener onItemClickListener = new ItemListAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(Items item) {
            if (getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                ((MainActivity) getActivity()).onItemClick(item);
            }
        }
    };
}
