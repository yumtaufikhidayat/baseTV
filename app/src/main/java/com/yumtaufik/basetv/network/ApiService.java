package com.yumtaufik.basetv.network;

import com.yumtaufik.basetv.models.TVShowsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Api.MOST_POPULAR)
    Call<TVShowsResponse> getMostPopularTVShows(
            @Query(Api.PARAMS_PAGE) int page
    );
}
