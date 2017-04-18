package com.sanja.example.twitterapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sanja.example.twitterapp.di.components.AppComponentContainer;
import com.sanja.example.twitterapp.home.DaggerHomeComponent;
import com.sanja.example.twitterapp.home.HomeComponent;
import com.sanja.example.twitterapp.home.HomeMvp;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements HomeMvp.View{

    @Inject HomeMvp.Presenter presenter;

    @BindView(R.id.tv_main) TextView tvMain;
    @BindView(R.id.rv_tweets) RecyclerView rvTweets;

    private TweetsAdapter tweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectDependencies();
        presenter.bind(this);

        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        tweetsAdapter = new TweetsAdapter();
        rvTweets.setAdapter(tweetsAdapter);
    }

    private void injectDependencies() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(AppComponentContainer.get())
                .build();
        homeComponent.inject(this);
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        tweetsAdapter.refreshTweets(tweets);
    }
}