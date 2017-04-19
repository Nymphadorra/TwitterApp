package com.sanja.example.twitterapp;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
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
    @BindView(R.id.toolbar) Toolbar toolbar;

    private TweetsAdapter tweetsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        injectDependencies();
        setupToolbar(toolbar);
        presenter.bind(this);

        rvTweets.setLayoutManager(new LinearLayoutManager(this));
        tweetsAdapter = new TweetsAdapter();
        rvTweets.setAdapter(tweetsAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case(R.id.menu_item_search):
                presenter.onSearchClicked();
                return true;
        }
        return true;
    }

    @Override
    public void showTweets(List<Tweet> tweets) {
        tweetsAdapter.refreshTweets(tweets);
    }

    private void injectDependencies() {
        HomeComponent homeComponent = DaggerHomeComponent.builder()
                .appComponent(AppComponentContainer.get())
                .build();
        homeComponent.inject(this);
    }
}