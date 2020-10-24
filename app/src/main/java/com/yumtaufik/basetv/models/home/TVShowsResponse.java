package com.yumtaufik.basetv.models.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVShowsResponse {

    @Expose
    @SerializedName("tv_shows")
    public List<TVShowsItems> tv_shows;
    @Expose
    @SerializedName("pages")
    public int pages;
    @Expose
    @SerializedName("page")
    public int page;
    @Expose
    @SerializedName("total")
    public String total;

    public List<TVShowsItems> getTv_shows() {
        return tv_shows;
    }

    public int getPages() {
        return pages;
    }

    public int getPage() {
        return page;
    }

    public String getTotal() {
        return total;
    }
}
