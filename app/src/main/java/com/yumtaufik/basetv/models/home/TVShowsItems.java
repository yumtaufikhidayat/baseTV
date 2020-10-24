package com.yumtaufik.basetv.models.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TVShowsItems implements Parcelable {

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

    protected TVShowsItems(Parcel in) {
        image_thumbnail_path = in.readString();
        status = in.readString();
        network = in.readString();
        country = in.readString();
        start_date = in.readString();
        permalink = in.readString();
        name = in.readString();
        id = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(image_thumbnail_path);
        parcel.writeString(status);
        parcel.writeString(network);
        parcel.writeString(country);
        parcel.writeString(start_date);
        parcel.writeString(permalink);
        parcel.writeString(name);
        parcel.writeInt(id);
    }
}
