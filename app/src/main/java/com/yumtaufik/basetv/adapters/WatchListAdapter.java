package com.yumtaufik.basetv.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ItemTvShowBinding;
import com.yumtaufik.basetv.listeners.WatchListListener;
import com.yumtaufik.basetv.models.home.TVShowsItems;

import java.util.List;

public class WatchListAdapter extends RecyclerView.Adapter<WatchListAdapter.TVShowsViewHolder> {

    private final List<TVShowsItems> tvShowsItemsList;
    private LayoutInflater layoutInflater;
    private final WatchListListener watchListListener;

    public WatchListAdapter(List<TVShowsItems> tvShowsItemsList, WatchListListener watchListListener) {
        this.tvShowsItemsList = tvShowsItemsList;
        this.watchListListener = watchListListener;
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

    class TVShowsViewHolder extends RecyclerView.ViewHolder {

        ItemTvShowBinding itemTvShowBinding;

        public TVShowsViewHolder(ItemTvShowBinding itemTvShowBinding) {
            super(itemTvShowBinding.getRoot());
            this.itemTvShowBinding = itemTvShowBinding;
        }

        public void bindTVShows(TVShowsItems tvShowsItems) {
            itemTvShowBinding.setTvShow(tvShowsItems);
            itemTvShowBinding.executePendingBindings();
            itemTvShowBinding.getRoot().setOnClickListener(view -> watchListListener.onTVShowClicked(tvShowsItems));
            itemTvShowBinding.imgDelete.setOnClickListener(view -> watchListListener.removeTVShowFromWatchList(tvShowsItems, getAdapterPosition()));
            itemTvShowBinding.imgDelete.setVisibility(View.VISIBLE);
        }
    }
}
