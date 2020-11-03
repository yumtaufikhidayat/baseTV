package com.yumtaufik.basetv.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.yumtaufik.basetv.R;
import com.yumtaufik.basetv.databinding.ItemContainerEpisodesBinding;
import com.yumtaufik.basetv.models.details.Episodes;

import java.util.List;

public class EpisodesAdapter extends RecyclerView.Adapter<EpisodesAdapter.EpisodesViewHolder> {

    private final List<Episodes> episodesList;
    private LayoutInflater layoutInflater;

    public EpisodesAdapter(List<Episodes> episodesList) {
        this.episodesList = episodesList;
    }

    @NonNull
    @Override
    public EpisodesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemContainerEpisodesBinding itemContainerEpisodesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_episodes, parent, false);

        return new EpisodesViewHolder(itemContainerEpisodesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodesViewHolder holder, int position) {
        holder.bindEpisodes(episodesList.get(position));
    }

    @Override
    public int getItemCount() {
        return episodesList.size();
    }

    static class EpisodesViewHolder extends RecyclerView.ViewHolder {

        private final ItemContainerEpisodesBinding itemContainerEpisodesBinding;

        public EpisodesViewHolder(ItemContainerEpisodesBinding itemContainerEpisodesBinding) {
            super(itemContainerEpisodesBinding.getRoot());

            this.itemContainerEpisodesBinding = itemContainerEpisodesBinding;
        }

        public void bindEpisodes(Episodes episodes) {

            String title = "S";
            String season = String.valueOf(episodes.getSeason());
            if (season.length() == 1) {
                season = "0".concat(season);
            }

            String episodesNumber = String.valueOf(episodes.getEpisode());
            if (episodesNumber.length() == 1) {
                episodesNumber = "0".concat(episodesNumber);
            }

            episodesNumber = "E".concat(episodesNumber);
            title = title.concat(season).concat(episodesNumber);
            itemContainerEpisodesBinding.setTitle(title);
            itemContainerEpisodesBinding.setName(episodes.getName());
            itemContainerEpisodesBinding.setAirDate(episodes.getAirDate());
        }
    }
}
