package com.yumtaufik.basetv.models.home;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "tvShows")
public class TVShowsItems implements Parcelable {

    @PrimaryKey
    @Expose
    @SerializedName("id")
    public int id;
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

    public TVShowsItems() {
    }

    protected TVShowsItems(Parcel in) {
        id = in.readInt();
        image_thumbnail_path = in.readString();
        status = in.readString();
        network = in.readString();
        country = in.readString();
        start_date = in.readString();
        permalink = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(image_thumbnail_path);
        dest.writeString(status);
        dest.writeString(network);
        dest.writeString(country);
        dest.writeString(start_date);
        dest.writeString(permalink);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<TVShowsItems> CREATOR = new Creator<TVShowsItems>() {
        @Override
        public TVShowsItems createFromParcel(Parcel in) {
            return new TVShowsItems(in);
        }

        @Override
        public TVShowsItems[] newArray(int size) {
            return new TVShowsItems[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_thumbnail_path() {
        return image_thumbnail_path;
    }

    public void setImage_thumbnail_path(String image_thumbnail_path) {
        this.image_thumbnail_path = image_thumbnail_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetwork() {
        return network;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
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
