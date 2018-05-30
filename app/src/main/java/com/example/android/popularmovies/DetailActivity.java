package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private static final String MOVIE_KEY = "movie_key";

    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mPlotSynopsis;
    private TextView mUserRating;
    private ImageView mPoster;
    private String mPosterURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOriginalTitle = (TextView) findViewById(R.id.tvOriginalTitle);
        mReleaseDate = (TextView) findViewById(R.id.tvReleaseDate);
        mPlotSynopsis = (TextView) findViewById(R.id.tvPlotSynopsis);
        mUserRating = (TextView) findViewById(R.id.tvUserRating);
        mPoster = (ImageView) findViewById(R.id.ivPoster);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(MOVIE_KEY);

        mOriginalTitle.setText(movie.getOriginalTitle());
        mReleaseDate.setText(movie.getReleaseDate());
        mPlotSynopsis.setText(movie.getPlotSynopsis());
        mUserRating.setText(String.valueOf(movie.getUserRating()) + "/10");
        mPosterURL = movie.getMoviePosterUrl();

        //Load the movies poster again using Picasso
        Picasso.with(DetailActivity.this)
                .load(mPosterURL)
                .into(mPoster);

    }
}
