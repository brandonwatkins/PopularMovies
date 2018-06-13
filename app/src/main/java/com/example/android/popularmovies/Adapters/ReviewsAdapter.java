package com.example.android.popularmovies.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.ViewHolders.ReviewsViewHolder;

import java.util.ArrayList;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {

    private static final String LOG_TAG = ReviewsAdapter.class.getSimpleName();

    //Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    private Context mContext;
    private ArrayList<String> mReviews;

    public ReviewsAdapter(Context context, ArrayList<String> reviews) {
        this.mContext = context;
        this.mReviews = reviews;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_reviews, parent, false);
        ReviewsViewHolder viewHolder = new ReviewsViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, final int position) {

        holder.tvReview.setText(mReviews.get(position));

    }

    @Override
    public int getItemCount() {
        if (mReviews == null)
            return 0;
        else
            return  mReviews.size();
    }

    public void deliverResults(ArrayList<String> data) {
        // Remove existing data from the ArrayList
        mReviews.clear();
        // Add all the new data passed in into the ArrayList
        mReviews.addAll(data);
        notifyDataSetChanged();
    }

}
