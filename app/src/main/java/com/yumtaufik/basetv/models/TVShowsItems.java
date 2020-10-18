package com.yumtaufik.basetv.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVShowsItems {
    @Expose
    @SerializedName("image_thumbnail_path")
    public String image_thumbnail_path;
    @Expose
    @SerializedName("status")
    public String status;
    @Expose
    @SerializedName("network")
    public String network;
    @Expose
    @SerializedName("country")
    public String country;
    @Expose
    @SerializedName("start_date")
    public String start_date;
    @Expose
    @SerializedName("permalink")
    public String permalink;
    @Expose
    @SerializedName("name")
    public String name;
    @Expose
    @SerializedName("id")
    public int id;

    public String getImage_thumbnail_path() {
        return image_thumbnail_path;
    }

    public String getStatus() {
        return status;
    }

    public String getNetwork() {
        return network;
    }

    public String getCountry() {
        return country;
    }

    public String getStart_date() {
        return start_date;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}
