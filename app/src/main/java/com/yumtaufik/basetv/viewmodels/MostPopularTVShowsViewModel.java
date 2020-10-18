package com.yumtaufik.basetv.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yumtaufik.basetv.models.TVShowsResponse;
import com.yumtaufik.basetv.repositories.MostPopularTVShowsRepository;

public class MostPopularTVShowsViewModel extends ViewModel {

    private MostPopularTVShowsRepository mostPopularTVShowsRepository = new MostPopularTVShowsRepository();

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {
        return mostPopularTVShowsRepository.getMostPopularTVShows(page);
    }
}
