package com.example.nicky.saltsidecodingexercise.ui.screen;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicky.saltsidecodingexercise.R;
import com.example.nicky.saltsidecodingexercise.data.Items;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NICKY on 09-09-2018.
 */

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ViewHolder> {
    List<Items> items = new ArrayList<>();
    OnItemClickListener onItemClickListener;

    public ItemListAdapter(List<Items> items) {
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.list_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Items items = this.items.get(position);
        holder.mTitle.setText(items.getTitle());
        holder.mDescription.setText(items.getDescription());
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setValues(List<Items> data) {
        if (items == null) {
            this.items = data;
            notifyItemRangeInserted(0, data.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ItemListAdapter.this.items.size();
                }

                @Override
                public int getNewListSize() {
                    return data.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ItemListAdapter.this.items.get(oldItemPosition).getId() ==
                            data.get(newItemPosition).getId();
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Items newItem = data.get(newItemPosition);
                    Items old = data.get(oldItemPosition);
                    return newItem.getId() == old.getId();
                }
            });
            this.items = data;
            result.dispatchUpdatesTo(this);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTitle;
        private final TextView mDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.title);
            mDescription = itemView.findViewById(R.id.description);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClick(items.get(getAdapterPosition()));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Items item);
    }
}
