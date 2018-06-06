package com.example.android.popularmovies.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.android.popularmovies.R;

/**
 * ViewHolder Class
 */
public class ViewHolder extends RecyclerView.ViewHolder {

    public ImageView mPoster;

    public ViewHolder (View view) {
        super(view);
        mPoster = view.findViewById(R.id.poster);
    }
}
