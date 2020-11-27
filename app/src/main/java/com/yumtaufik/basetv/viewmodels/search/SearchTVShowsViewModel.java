package com.yumtaufik.basetv.viewmodels.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yumtaufik.basetv.models.home.TVShowsResponse;
import com.yumtaufik.basetv.repositories.search.SearchTVShowsRepository;

public class SearchTVShowsViewModel extends ViewModel {

    private final SearchTVShowsRepository searchTVShowsRepository = new SearchTVShowsRepository();

    public LiveData<TVShowsResponse> searchTVShows(String query, int page) {
        return searchTVShowsRepository.searchTVShow(query, page);
    }
}
