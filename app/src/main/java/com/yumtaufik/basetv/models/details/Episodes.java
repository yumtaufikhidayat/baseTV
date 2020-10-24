package com.yumtaufik.basetv.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Episodes {

    @Expose
    @SerializedName("air_date")
    private String airDate;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("episode")
    private int episode;
    @Expose
    @SerializedName("season")
    private int season;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEpisode() {
        return episode;
    }

    public void setEpisode(int episode) {
        this.episode = episode;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }
}
