package com.sanja.example.twitterapp.settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchQueriesAdapter extends RecyclerView.Adapter<SearchQueriesAdapter.SearchQueriesViewHolder>{

    private List<String> searchQueries;
    private final ItemClickListener listener;

    public SearchQueriesAdapter(ItemClickListener listener){
        this.searchQueries = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public SearchQueriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_query_item, parent, false);
        return new SearchQueriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchQueriesViewHolder holder, int position) {
        holder.searchQuery.setText(searchQueries.get(position));
    }

    @Override
    public int getItemCount() {
        return searchQueries.size();
    }

    public void addNewSearchQuery(String searchQuery) {
        searchQueries.add(searchQuery);
        notifyDataSetChanged();
    }

    class SearchQueriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_search_query) TextView searchQuery;

        public SearchQueriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick
        public void onRootClicked() {
            listener.onItemClicked(getAdapterPosition());
        }
    }
}