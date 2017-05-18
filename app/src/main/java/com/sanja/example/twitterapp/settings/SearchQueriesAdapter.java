package com.sanja.example.twitterapp.settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sanja.example.twitterapp.ItemClickListener;
import com.sanja.example.twitterapp.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

public class SearchQueriesAdapter extends RecyclerView.Adapter<SearchQueriesAdapter.SearchQueriesViewHolder>
        implements ItemTouchHelperAdapter {

    public interface ItemRemoveListener {
        void onItemRemoved(int position);

    }

    private List<SearchQuery> searchQueries;
    private final OnStartDragListener onStartDragListener;
    private final ItemRemoveListener itemRemoveListener;
    private final ItemClickListener itemClickListener;


    public SearchQueriesAdapter(OnStartDragListener onStartDragListener, ItemRemoveListener itemRemoveListener, ItemClickListener itemClickListener) {
        this.searchQueries = new ArrayList<>();
        this.onStartDragListener = onStartDragListener;
        this.itemRemoveListener = itemRemoveListener;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SearchQueriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_query_item, parent, false);
        return new SearchQueriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchQueriesViewHolder holder, int position) {
        holder.searchQuery.setText(searchQueries.get(position).getSearchName());
    }

    @Override
    public int getItemCount() {
        return searchQueries.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(searchQueries, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(searchQueries, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        searchQueries.remove(position);
        notifyItemRemoved(position);
        itemRemoveListener.onItemRemoved(position);
    }

    public void refreshSearchQueries(List<SearchQuery> searchQueries) {
        this.searchQueries.clear();
        this.searchQueries.addAll(searchQueries);
        notifyDataSetChanged();
    }

    public void addItem(SearchQuery sq) {
        this.searchQueries.add(sq);
        notifyItemInserted(getItemCount() - 1);
    }

    class SearchQueriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_search_query) TextView searchQuery;

        public SearchQueriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnTouch(R.id.drag_handle)
        public boolean onDragHandlePressed() {
            onStartDragListener.onStartDrag(this);
            return true;
        }

        @OnClick
        public void onSQClicked(){
            itemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}