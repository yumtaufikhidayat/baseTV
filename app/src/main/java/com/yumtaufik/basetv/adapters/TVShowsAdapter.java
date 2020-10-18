package com.yumtaufik.basetv.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ItemTvShowBinding;
import com.yumtaufik.basetv.models.TVShowsItems;

import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder> {

    private List<TVShowsItems> tvShowsItemsList;
    private LayoutInflater layoutInflater;

    public TVShowsAdapter(List<TVShowsItems> tvShowsItemsList) {
        this.tvShowsItemsList = tvShowsItemsList;
    }

    @NonNull
    @Override
    public TVShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_tv_show, parent, false
        );

        return new TVShowsViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowsViewHolder holder, int position) {

        holder.bindTVShows(tvShowsItemsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowsItemsList.size();
    }

    static class TVShowsViewHolder extends RecyclerView.ViewHolder {

        ItemTvShowBinding itemTvShowBinding;

        public TVShowsViewHolder(ItemTvShowBinding itemTvShowBinding) {
            super(itemTvShowBinding.getRoot());
            this.itemTvShowBinding = itemTvShowBinding;
        }

        public void bindTVShows(TVShowsItems tvShowsItems) {
            itemTvShowBinding.setTvShow(tvShowsItems);
            itemTvShowBinding.executePendingBindings();
        }
    }
}
