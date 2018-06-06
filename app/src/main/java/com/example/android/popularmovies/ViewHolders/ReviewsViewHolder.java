package com.example.android.popularmovies.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.R;

/**
 * TrailerViewHolder Class
 */
public class ReviewsViewHolder extends RecyclerView.ViewHolder {

    public TextView tvReview;

    public ReviewsViewHolder(View view) {
        super(view);
        tvReview = view.findViewById(R.id.tvReviews);
    }
}
