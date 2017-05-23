package com.sanja.example.twitterapp.home;

import com.google.gson.annotations.SerializedName;

public class SearchMetaData {

    @SerializedName("completed_in") private double completedIn;
    @SerializedName("max_id") private long maxId;
    @SerializedName("max_id_str") private String maxIdString;
    @SerializedName("next_results") private String nextResults;
    @SerializedName("query") private String query;
    @SerializedName("refresh_url") private String refreshUrl;
    @SerializedName("count") private int count;
    @SerializedName("since_id") private int sinceId;
    @SerializedName("since_id_str") private String sinceIdString;

    public double getCompletedIn() {
        return completedIn;
    }

    public long getMaxId() {
        return maxId;
    }

    public String getMaxIdString() {
        return maxIdString;
    }

    public String getNextResults() {
        return nextResults;
    }

    public String getQuery() {
        return query;
    }

    public String getRefreshUrl() {
        return refreshUrl;
    }

    public int getCount() {
        return count;
    }

    public int getSinceId() {
        return sinceId;
    }

    public String getSinceIdString() {
        return sinceIdString;
    }
}