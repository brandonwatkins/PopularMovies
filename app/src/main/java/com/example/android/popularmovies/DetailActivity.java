package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.Adapters.MovieAdapter;
import com.example.android.popularmovies.Adapters.TrailerAdapter;
import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;
import com.example.android.popularmovies.Tasks.RetrieveTrailersTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private static final String MOVIE_KEY = "movie_key";

    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mPlotSynopsis;
    private TextView mUserRating;
    private ImageView mPoster;
    private String mPosterURL;

    private TrailerAdapter mTrailerAdapter;
    private RecyclerView mTrailersRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOriginalTitle = findViewById(R.id.tvOriginalTitle);
        mReleaseDate = findViewById(R.id.tvReleaseDate);
        mPlotSynopsis = findViewById(R.id.tvPlotSynopsis);
        mUserRating = findViewById(R.id.tvUserRating);
        mPoster = findViewById(R.id.ivPoster);
        mTrailersRecyclerView = findViewById(R.id.rvTrailers);

        ArrayList<String> mTrailers = new ArrayList<>();

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(MOVIE_KEY);

        String outOfTen = getString(R.string.out_of_ten);

        mOriginalTitle.setText(movie.getmOriginalTitle());
        mReleaseDate.setText(movie.getReleaseYear(movie.getmReleaseDate()));
        mPlotSynopsis.setText(movie.getmPlotSynopsis());
        mUserRating.setText(String.valueOf(movie.getmUserRating()) + outOfTen);
        mPosterURL = movie.getmMoviePosterUrl();

        String id = movie.getmMovieId();

        Log.d("DETAILS_ACTIVITY", "ID num:" + id);

        //Load the movies poster again using Picasso
        Picasso.with(DetailActivity.this)
                .load(mPosterURL)
                .into(mPoster);



        mTrailersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTrailerAdapter = new TrailerAdapter(this, mTrailers);
        mTrailersRecyclerView.setAdapter(mTrailerAdapter);

        RetrieveTrailersTask retrieveTrailersTask = new RetrieveTrailersTask(mTrailerAdapter);
        retrieveTrailersTask.execute(id);

    }
}
