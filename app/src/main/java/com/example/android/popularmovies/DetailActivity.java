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

        mOriginalTitle = findViewById(R.id.tvOriginalTitle);
        mReleaseDate = findViewById(R.id.tvReleaseDate);
        mPlotSynopsis = findViewById(R.id.tvPlotSynopsis);
        mUserRating = findViewById(R.id.tvUserRating);
        mPoster = findViewById(R.id.ivPoster);

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(MOVIE_KEY);

        String outOfTen = getString(R.string.out_of_ten);

        mOriginalTitle.setText(movie.getmOriginalTitle());
        mReleaseDate.setText(movie.getReleaseYear(movie.getmReleaseDate()));
        mPlotSynopsis.setText(movie.getmPlotSynopsis());
        mUserRating.setText(String.valueOf(movie.getmUserRating()) + outOfTen);
        mPosterURL = movie.getmMoviePosterUrl();

        //Load the movies poster again using Picasso
        Picasso.with(DetailActivity.this)
                .load(mPosterURL)
                .into(mPoster);

    }
}
