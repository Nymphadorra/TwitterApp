package com.sanja.example.twitterapp.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sanja.example.twitterapp.app.ItemClickListener;
import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.app.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TweetsRecyclerAdapter extends RecyclerView.Adapter<TweetsRecyclerAdapter.TweetsAdapterViewHolder>{

    private final String errorTweetDate;
    private final List<Tweet> tweets;
    private final ItemClickListener listener;
    private final Picasso picasso;

    public TweetsRecyclerAdapter(Context context, ItemClickListener listener, Picasso picasso) {
        this.errorTweetDate = context.getString(R.string.error_tweet_date);
        this.tweets = new ArrayList<>();
        this.listener = listener;
        this.picasso = picasso;
    }

    @Override
    public TweetsAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweets_list_item, parent, false);

        return new TweetsAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TweetsAdapterViewHolder holder, int position) {
        Tweet tweet = tweets.get(position);
        setUserPhoto(tweet.getUser().getProfilePhotoUrl(), holder.ivProfile);
        holder.tvUserName.setText(tweet.getUser().getName());
        holder.tvUserScreenName.setText(tweet.getUser().getScreenName());
        holder.tvTweetContent.setText(tweet.getText());
        holder.tvDate.setText(Utils.apiDateToUIDate(tweet.getDate(), errorTweetDate));
        holder.tvFavorites.setText(String.valueOf(tweet.getFavoriteCount()));
        holder.tvRetweets.setText(String.valueOf(tweet.getRetweetCount()));
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

    public void addMoreTweets(List<Tweet> tweets) {
        this.tweets.addAll(tweets);
        notifyDataSetChanged();
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    private void setUserPhoto(String photoUrl, ImageView image) {
        picasso.load(photoUrl)
                .placeholder(R.drawable.ic_person_black)
                .resize(80, 80)
                .into(image);
    }

    class TweetsAdapterViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.iv_profile) ImageView ivProfile;
        @BindView(R.id.tv_user_name) TextView tvUserName;
        @BindView(R.id.tv_user_screen_name) TextView tvUserScreenName;
        @BindView(R.id.tv_tweet_content) TextView tvTweetContent;
        @BindView(R.id.tv_date) TextView tvDate;
        @BindView(R.id.tv_favorites) TextView tvFavorites;
        @BindView(R.id.tv_retweets) TextView tvRetweets;

        public TweetsAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onRootClicked() {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}