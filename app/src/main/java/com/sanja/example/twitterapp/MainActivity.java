package com.sanja.example.twitterapp;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class MainActivity extends BaseActivity {

    private APIService apiService;
    private APIServiceAuth apiServiceAuth;

    @BindView(R.id.tv_main) TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        apiServiceAuth = Dependencies.createAPIServiceAuth();
        apiService = Dependencies.createAPIService();

        apiServiceAuth.createToken("client_credentials").enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                Timber.d("Response: %s", response.body().getAccessToken());
                tvMain.setText(response.body().getAccessToken());
                search(response.body().getAccessToken());
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Timber.d("Failure!");
            }
        });



    }

    private void search(String accessToken) {
        apiService.searchTweets("spacex", "Bearer " + accessToken).enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                if(response.isSuccessful()) {
                    tvMain.setText(response.body().getTweets().get(0).getText());
                } else {
                    tvMain.setText("Error setting text");

                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
                tvMain.setText("Error failure");
            }
        });

    }
}