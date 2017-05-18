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

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    public void refreshTweets(List<Tweet> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public void addMoreTweets(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }
}