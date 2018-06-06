package com.example.android.popularmovies.Adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.popularmovies.R;
import com.example.android.popularmovies.ViewHolders.TrailerViewHolder;

import java.util.ArrayList;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    private static final String LOG_TAG = TrailerAdapter.class.getSimpleName();

    //Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    private Context mContext;
    private ArrayList<String> mTrailers;

    public TrailerAdapter(Context context, ArrayList<String> trailers) {
        this.mContext = context;
        this.mTrailers = trailers;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_trailers, parent, false);
        TrailerViewHolder viewHolder = new TrailerViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TrailerViewHolder holder, final int position) {

        holder.trailerName.setText("Trailer " + (position + 1));

        holder.trailerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Clicked item: " + position);

                String trailerURL = mTrailers.get(position);

                // Create intent with trailers URL. Found help with the link below.
                // Source: https://stackoverflow.com/questions/574195/android-youtube-app-play-video-intent

                Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailerURL));
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://www.youtube.com/watch?v=" + trailerURL));
                try {
                    mContext.startActivity(appIntent);
                } catch (ActivityNotFoundException ex) {
                    mContext.startActivity(webIntent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mTrailers == null)
            return 0;
        else
            return  mTrailers.size();
    }

    public void deliverResults(ArrayList<String> data) {
        //Remove existing data from the ArrayList
        mTrailers.clear();
        //Add all the new data passed in into the ArrayList
        mTrailers.addAll(data);
        notifyDataSetChanged();
    }

}
