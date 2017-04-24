package com.sanja.example.twitterapp;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("name") private String name;
    @SerializedName("screen_name") private String screenName;
    @SerializedName("profile_image_url_https") private String profilePhotoUrl;

    public String getName() {
        return name;
    }

    public String getScreenName() {
        return "@" + screenName; // TODO string values!
    }

    public String getProfilePhotoUrl() {
        return profilePhotoUrl;
    }
}