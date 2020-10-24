package com.yumtaufik.basetv.repositories.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yumtaufik.basetv.models.details.TvShowDetailsResponse;
import com.yumtaufik.basetv.network.ApiClient;
import com.yumtaufik.basetv.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class TVShowDetailsRepository {

    public LiveData<TvShowDetailsResponse> getTvShowDetail(String tvShowId) {

        MutableLiveData<TvShowDetailsResponse> data = new MutableLiveData<>();
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.getTvShowDetails(tvShowId).enqueue(new Callback<TvShowDetailsResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<TvShowDetailsResponse> call, Response<TvShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<TvShowDetailsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
