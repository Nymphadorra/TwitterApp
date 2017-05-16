package com.sanja.example.twitterapp.settings;

public interface ItemTouchHelperAdapter {

    // Called when an item has been dragged far enough to trigger a move.
    void onItemMove(int fromPosition, int toPosition);

    // Called when an item has been dismissed by a swipe.
    void onItemDismiss(int position);
}