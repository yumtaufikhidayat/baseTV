package com.yumtaufik.basetv.viewmodels.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yumtaufik.basetv.models.home.TVShowsResponse;
import com.yumtaufik.basetv.repositories.home.MostPopularTVShowsRepository;

public class MostPopularTVShowsViewModel extends ViewModel {

    private final MostPopularTVShowsRepository mostPopularTVShowsRepository = new MostPopularTVShowsRepository();

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }
}
