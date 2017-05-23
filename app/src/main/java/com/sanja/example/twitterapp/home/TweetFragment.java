package com.sanja.example.twitterapp.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.app.Utils;
import com.sanja.example.twitterapp.di.components.AppComponentContainer;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetFragment extends Fragment {

    private static final String ARG_TWEET = "tweet";

    @Inject Picasso picasso;

    @BindView(R.id.iv_profile) ImageView ivProfile;
    @BindView(R.id.tv_user_name) TextView tvUserName;
    @BindView(R.id.tv_user_screen_name) TextView tvUserScreenName;
    @BindView(R.id.tv_date) TextView tvDate;
    @BindView(R.id.tv_tweet_content) TextView tvTweetContent;
    @BindView(R.id.tv_favorites) TextView tvFavorites;
    @BindView(R.id.tv_retweets) TextView tvRetweets;

    @BindString(R.string.error_tweet_date) String errorTweetDate;

    private Tweet tweet;

    public TweetFragment() {
        // Required empty public constructor
    }

    public static TweetFragment createInstance(Tweet tweet) {
        TweetFragment fragment = new TweetFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TWEET, tweet);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppComponentContainer.get().inject(this);
        if (getArguments() != null) {
            this.tweet = getArguments().getParcelable(ARG_TWEET);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_tweet, container, false);
        ButterKnife.bind(this, rootView);

        picasso.load(tweet.getUser().getProfilePhotoUrl())
                .placeholder(R.drawable.ic_person_black)
                .resize(80, 80)
                .into(ivProfile);
        tvUserName.setText(tweet.getUser().getName());
        tvUserScreenName.setText(tweet.getUser().getScreenName());
        tvDate.setText(Utils.apiDateToUIDate(tweet.getDate(), errorTweetDate));
        tvTweetContent.setText(tweet.getText());
        tvFavorites.setText(String.valueOf(tweet.getFavoriteCount()));
        tvRetweets.setText(String.valueOf(tweet.getRetweetCount()));

        return rootView;
    }
}