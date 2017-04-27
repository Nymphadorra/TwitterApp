package com.sanja.example.twitterapp;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Tweet implements Parcelable {

    @SerializedName("user") private User user;
    @SerializedName("created_at") private String date;
    @SerializedName("text") private String text;
    @SerializedName("favorite_count") private int favoriteCount;
    @SerializedName("retweet_count") private int retweetCount;

    public User getUser() {
        return user;
    }

    public String getDate() {
        return date;
    }

    public String getText() {
        return text;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeString(date);
        dest.writeString(text);
        dest.writeInt(favoriteCount);
        dest.writeInt(retweetCount);
    }

    public Tweet(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        date = in.readString();
        text = in.readString();
        favoriteCount = in.readInt();
        retweetCount = in.readInt();
    }

    public static final Parcelable.Creator<Tweet> CREATOR =
            new Parcelable.Creator<Tweet>() {

                @Override
                public Tweet createFromParcel(Parcel source) {
                    return new Tweet(source);
                }

                @Override
                public Tweet[] newArray(int size) {
                    return new Tweet[size];
                }
            };
}