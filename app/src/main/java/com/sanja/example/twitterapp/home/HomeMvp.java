package com.sanja.example.twitterapp.home;

import com.sanja.example.twitterapp.app.BasePresenter;
import com.sanja.example.twitterapp.Tweet;

import java.util.List;

public class HomeMvp {

    public interface View{

        void showTweets(List<Tweet> tweets);

        void showMoreTweets(List<Tweet> tweets);

        void showListLayout();

        void setCurrentTweetPosition(int position);

        void showPagerLayout();

        void showNetworkError();

        void showEmptyResponse();

        void startListAutoScroll(final int listAutoScrollDelay);

        void startPagerAutoScroll(final int pagerAutoScrollDelay);

        void stopListAutoScroll();

        void stopPagerAutoScroll();
    }

    public interface Presenter extends BasePresenter<View> {

        void onListAutoScrollClicked();

        void onPagerAutoScrollClicked();

        void onListLayoutClicked();

        void onPagerLayoutClicked();

        void onSettingsClicked();

        void onPullToRefresh();

        void onReconnectClicked();

        void onLoadMoreTweets();

        boolean isLoadingInProgress();

        boolean hasLoadedAllItems();
    }
}