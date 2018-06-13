package com.example.android.popularmovies.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.R;

/**
 * TrailerViewHolder Class
 */
public class TrailerViewHolder extends RecyclerView.ViewHolder {

    public TextView trailerName;

    public TrailerViewHolder(View view) {
        super(view);
        trailerName = view.findViewById(R.id.tvTrailer);
    }
}
