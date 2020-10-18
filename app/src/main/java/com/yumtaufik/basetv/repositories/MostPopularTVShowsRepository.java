package com.yumtaufik.basetv.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.yumtaufik.basetv.models.TVShowsResponse;
import com.yumtaufik.basetv.network.ApiClient;
import com.yumtaufik.basetv.network.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MostPopularTVShowsRepository {

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page) {

        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
        apiService.getMostPopularTVShows(page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            @EverythingIsNonNull
            public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            @EverythingIsNonNull
            public void onFailure(Call<TVShowsResponse> call, Throwable t) {
                data.setValue(null);
                Log.i("onFailurePopularTVShows", "onFailurePopularTVShows: " + t.getLocalizedMessage());
            }
        });

        return data;
    }
}
