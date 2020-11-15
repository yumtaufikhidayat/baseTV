package com.yumtaufik.basetv.viewmodels.watchlist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.yumtaufik.basetv.database.TvShowDatabase;
import com.yumtaufik.basetv.models.home.TVShowsItems;

import java.util.List;

import io.reactivex.Flowable;

public class WatchListViewModel extends AndroidViewModel {

    private TvShowDatabase tvShowDatabase;

    public WatchListViewModel(@NonNull Application application) {
        super(application);

        tvShowDatabase = TvShowDatabase.getTvShowDatabase(application);
    }

    public Flowable<List<TVShowsItems>> loadWatchList() {
        return tvShowDatabase.tvShowDao().getWatchList();
    }
}
