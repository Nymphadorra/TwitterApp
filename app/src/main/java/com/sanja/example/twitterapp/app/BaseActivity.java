package com.sanja.example.twitterapp.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.sanja.example.twitterapp.R;
import com.sanja.example.twitterapp.di.components.AppComponent;
import com.sanja.example.twitterapp.di.components.AppComponentContainer;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    private TextView toolbarTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies(AppComponentContainer.get());
    }

    protected abstract void injectDependencies(AppComponent appComponent);

    protected void setupToolbar(@NonNull Toolbar toolbar, @StringRes int toolbarTitleResId, boolean isHomeEnabled) {
        setSupportActionBar(toolbar);
        toolbarTitle = ButterKnife.findById(toolbar, R.id.tv_toolbar_title);
        setToolbarTitle(toolbarTitleResId);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(isHomeEnabled);
    }

    protected void setToolbarTitle(@StringRes int toolbarTitleResId) {
        toolbarTitle.setText(getString(toolbarTitleResId));
    }

    protected void showToast(@StringRes int stringResId) {
        showToast(getString(stringResId));
    }

    protected void showToast(String toastMessage) {
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show();
    }
}