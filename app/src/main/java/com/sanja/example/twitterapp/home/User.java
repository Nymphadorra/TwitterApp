package com.sanja.example.twitterapp.home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("name") private String name;
    @SerializedName("screen_name") private String screenName;
    @SerializedName("profile_image_url_https") private String profilePhotoUrl;

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return "@" + screenName;
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(screenName);
        dest.writeString(profilePhotoUrl);
    }

    public User(Parcel in) {
        name = in.readString();
        screenName = in.readString();
        profilePhotoUrl = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}