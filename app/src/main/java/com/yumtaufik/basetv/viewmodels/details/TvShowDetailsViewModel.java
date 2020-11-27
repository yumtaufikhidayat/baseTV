package com.yumtaufik.basetv.viewmodels.details;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.yumtaufik.basetv.database.TvShowDatabase;
import com.yumtaufik.basetv.models.details.TvShowDetailsResponse;
import com.yumtaufik.basetv.models.home.TVShowsItems;
import com.yumtaufik.basetv.repositories.details.TVShowDetailsRepository;

import io.reactivex.Completable;
import io.reactivex.Flowable;

public class TvShowDetailsViewModel extends AndroidViewModel {

    private final TVShowDetailsRepository tvShowDetailsRepository;
    private final TvShowDatabase tvShowDatabase;

    public TvShowDetailsViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailsRepository = new TVShowDetailsRepository();
        tvShowDatabase = TvShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTvShowDetail(tvShowId);
    }

    public Completable addToWatchList(TVShowsItems tvShowsItems) {
        return tvShowDatabase.tvShowDao().addToWatchList(tvShowsItems);
    }

    public Flowable<TVShowsItems> getTVShowFromWatchList(String tvShowId) {
        return tvShowDatabase.tvShowDao().getTVShowFromWatchList(tvShowId);
    }

    public Completable removeTVShowFromWatchList(TVShowsItems tvShowsItems) {
        return tvShowDatabase.tvShowDao().removeFromWatchList(tvShowsItems);
    }
}
