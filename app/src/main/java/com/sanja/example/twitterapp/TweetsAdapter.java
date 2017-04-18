package com.sanja.example.twitterapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TweetsAdapter extends RecyclerView.Adapter<TweetsAdapter.TweetsAdapterViewHolder> {

    private final List<Tweet> tweets;

    public TweetsAdapter(){
        this.tweets = new ArrayList<>();
    }

    @Override
    public TweetsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweets_list_item, parent, false);

        return new TweetsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetsAdapterViewHolder holder, int position) {
        holder.tvTweetContent.setText(tweets.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public void refreshTweets(List<Tweet> tweets) {
        this.tweets.clear();
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public class TweetsAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_tweet_content) TextView tvTweetContent;

        public TweetsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}