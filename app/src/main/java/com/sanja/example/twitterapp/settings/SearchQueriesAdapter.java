package com.sanja.example.twitterapp.settings;

import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
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

    private List<SearchQuery> searchQueries;
    private final OnStartDragListener onStartDragListener;
    private final ItemClickListener itemClickListener;
    private int selectedItemPosition;


    public SearchQueriesAdapter(OnStartDragListener onStartDragListener, ItemClickListener itemClickListener) {
        this.searchQueries = new ArrayList<>();
        this.onStartDragListener = onStartDragListener;
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
        SearchQuery sq = searchQueries.get(position);
        holder.searchQuery.setText(sq.getSearchName());
        int color = Color.parseColor("navy");
        if (sq.isSelected()) {
            int colorWithAlpha = ColorUtils.setAlphaComponent(color, 70);
            holder.llRoot.setBackgroundColor(colorWithAlpha);
        } else {
            holder.llRoot.setBackgroundColor(Color.parseColor("white"));
        }
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

    public void removeItem(SearchQuery searchQuery) {
        int position = searchQueries.indexOf(searchQuery);
        this.searchQueries.remove(searchQuery);
        notifyItemRemoved(position);
    }

    public SearchQuery getItem(int position) {
        return searchQueries.get(position);
    }

    public List<SearchQuery> getSearchQueries() {
        return searchQueries;
    }

    public int setSelectedItemPosition(SearchQuery sq) {
        refreshList(sq);
        return searchQueries.indexOf(sq);
    }

    public void setSelectedItemPosition(int position) {
        selectedItemPosition = position;
    }

    private void refreshList(SearchQuery sq) {
        for (int i = 0; i < searchQueries.size(); i++){
            if(searchQueries.get(i) != sq){
                searchQueries.get(i).unmarkAsSelected();
            }
        }
        notifyDataSetChanged();
    }

    class SearchQueriesViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ll_root) FrameLayout llRoot;
        @BindView(R.id.tv_search_query) TextView searchQuery;
        @BindView(R.id.iv_selected) ImageView ivSelected;

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
        public void onSQClicked() {
            itemClickListener.onItemClicked(getAdapterPosition());
        }
    }
}