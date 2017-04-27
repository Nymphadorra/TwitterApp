package com.sanja.example.twitterapp.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.sanja.example.twitterapp.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetsPagerAdapter extends FragmentStatePagerAdapter {
    List<Tweet> tweets;

    public TweetsPagerAdapter(FragmentManager fm) {
        super(fm);
        this.tweets = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        return TweetFragment.createInstance(tweets.get(position));
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    public void refreshTweets(List<Tweet> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }
}