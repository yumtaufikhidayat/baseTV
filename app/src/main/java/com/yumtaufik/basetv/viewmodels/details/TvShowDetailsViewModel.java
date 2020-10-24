package com.yumtaufik.basetv.viewmodels.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.yumtaufik.basetv.models.details.TvShowDetailsResponse;
import com.yumtaufik.basetv.repositories.details.TVShowDetailsRepository;

public class TvShowDetailsViewModel extends ViewModel {

    private final TVShowDetailsRepository tvShowDetailsRepository = new TVShowDetailsRepository();

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getTvShowDetail(tvShowId);
    }
}
