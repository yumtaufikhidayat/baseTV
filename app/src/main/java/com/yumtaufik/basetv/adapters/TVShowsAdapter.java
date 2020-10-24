package com.yumtaufik.basetv.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ItemTvShowBinding;
import com.yumtaufik.basetv.listeners.TvShowsListener;
import com.yumtaufik.basetv.models.home.TVShowsItems;

import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder> {

    private final List<TVShowsItems> tvShowsItemsList;
    private LayoutInflater layoutInflater;
    private final TvShowsListener tvShowsListener;

    public TVShowsAdapter(List<TVShowsItems> tvShowsItemsList, TvShowsListener tvShowsListener) {
        this.tvShowsItemsList = tvShowsItemsList;
        this.tvShowsListener = tvShowsListener;
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
            itemTvShowBinding.getRoot().setOnClickListener(view -> tvShowsListener.onTvShowClicked(tvShowsItems));
        }
    }
}
