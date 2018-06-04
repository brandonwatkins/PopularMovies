package com.example.android.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<ViewHolder> {

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    //Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movie) {
        this.mContext = context;
        this.mMovies = movie;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        ImageView imageView = holder.mPoster;

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "Clicked item: " + position);

                Movie movie = mMovies.get(position);

                //Create intent with movie object selected and pass it to DetailActivity
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(MOVIE_KEY, movie);
                mContext.startActivity(intent);
            }
        });

        //Picasso is used to load the movie poster url into the image view
        Picasso.with(mContext)
                .load(mMovies.get(position).getmMoviePosterUrl())
                .into(imageView);

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public void deliverResults(List<Movie> data) {
        //Remove existing data from the ArrayList
        mMovies.clear();
        //Add all the new data passed in into the ArrayList
        mMovies.addAll(data);
        notifyDataSetChanged();
    }

}
