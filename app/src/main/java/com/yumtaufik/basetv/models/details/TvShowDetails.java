package com.yumtaufik.basetv.models.details;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowDetails {

    @Expose
    @SerializedName("episodes")
    private List<Episodes> episodes;
    @Expose
    @SerializedName("pictures")
    private List<String> pictures;
    @Expose
    @SerializedName("genres")
    private List<String> genres;
    @Expose
    @SerializedName("rating_count")
    private String ratingCount;
    @Expose
    @SerializedName("rating")
    private String rating;
    @Expose
    @SerializedName("image_thumbnail_path")
    private String imageThumbnailPath;
    @Expose
    @SerializedName("image_path")
    private String imagePath;
    @Expose
    @SerializedName("youtube_link")
    private String youtubeLink;
    @Expose
    @SerializedName("network")
    private String network;
    @Expose
    @SerializedName("runtime")
    private int runtime;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("country")
    private String country;
    @Expose
    @SerializedName("start_date")
    private String startDate;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("url")
    private String url;
    @Expose
    @SerializedName("permalink")
    private String permalink;
    @Expose
    @SerializedName("name")
    private String name;
    @Expose
    @SerializedName("id")
    private int id;

    public List<Episodes> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episodes> episodes) {
        this.episodes = episodes;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermalink() {
        return permalink;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
