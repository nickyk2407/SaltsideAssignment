package com.example.nicky.saltsidecodingexercise.ui.screen;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.nicky.saltsidecodingexercise.R;
import com.example.nicky.saltsidecodingexercise.data.Items;

import static com.example.nicky.saltsidecodingexercise.ui.screen.MainActivity.SELECTED_ITEM;

/**
 * Created by NICKY on 09-09-2018.
 */

public class ItemDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        TextView description = view.findViewById(R.id.description);
        ImageView imageView = view.findViewById(R.id.imageView);
        ProgressBar progressBar = view.findViewById(R.id.progressbar);

        if (getArguments() != null && getArguments().containsKey(SELECTED_ITEM) && getArguments().get(SELECTED_ITEM) instanceof Items) {
            Items item = (Items) getArguments().get(SELECTED_ITEM);
            ((MainActivity) getActivity()).setActionBarTitle(item.getTitle());
            description.setText(item.getDescription());
            Glide.with(getActivity()).load(item.getImage())
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        }
        return view;
    }
}
