package com.yumtaufik.basetv.listeners;

import com.yumtaufik.basetv.models.home.TVShowsItems;

public interface WatchListListener {

    void onTVShowClicked(TVShowsItems tvShowsItems);

    void removeTVShowFromWatchList(TVShowsItems tvShowsItems, int position);
}
