package com.yumtaufik.basetv.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShowDetailsResponse {

    @Expose
    @SerializedName("tvShow")
    private TvShowDetails tvShowDetails;

    public TvShowDetails getTvShow() {
        return tvShowDetails;
    }

    public void setTvShow(TvShowDetails tvShowDetails) {
        this.tvShowDetails = tvShowDetails;
    }
}
