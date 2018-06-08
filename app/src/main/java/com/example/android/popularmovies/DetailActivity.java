package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmovies.Adapters.ReviewsAdapter;
import com.example.android.popularmovies.Adapters.TrailerAdapter;
import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;
import com.example.android.popularmovies.Tasks.AddToFavouritesTask;
import com.example.android.popularmovies.Tasks.RetrieveReviewsTask;
import com.example.android.popularmovies.Tasks.RetrieveTrailersTask;
import com.example.android.popularmovies.Tasks.UpdateIsFavouriteTask;
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
    private ImageButton mFavBtn;

    private TrailerAdapter mTrailerAdapter;
    private RecyclerView mTrailersRecyclerView;

    private ReviewsAdapter mReviewsAdapter;
    private RecyclerView mReviewsRecyclerView;

    PopularMoviesDb database;


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
        mReviewsRecyclerView = findViewById(R.id.rvReviews);
        mFavBtn = findViewById(R.id.ibFavButton);


        ArrayList<String> mTrailers = new ArrayList<>();
        ArrayList<String> mReviews = new ArrayList<>();

        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(MOVIE_KEY);

        String outOfTen = getString(R.string.out_of_ten);

        mOriginalTitle.setText(movie.getmOriginalTitle());
        mReleaseDate.setText(movie.getReleaseYear(movie.getmReleaseDate()));
        mPlotSynopsis.setText(movie.getmPlotSynopsis());
        mUserRating.setText(String.valueOf(movie.getmUserRating()) + outOfTen);
        mPosterURL = movie.getmMoviePosterUrl();

        int id = movie.getmMovieId();

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

        mReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReviewsAdapter = new ReviewsAdapter(this, mReviews);
        mReviewsRecyclerView.setAdapter(mReviewsAdapter);

        RetrieveReviewsTask retrieveReviewsTask = new RetrieveReviewsTask(mReviewsAdapter);
        retrieveReviewsTask.execute(id);

        mFavBtn.setOnClickListener(new FavouritesOnClickListener(movie));

        // Get reference to the apps database
        database = PopularMoviesDb.getDatabase(this);

    }


    public class FavouritesOnClickListener implements View.OnClickListener {

        Movie movie;
        public FavouritesOnClickListener(Movie movie) {
            this.movie = movie;
        }

        @Override
        public void onClick(View v) {
            new AddToFavouritesTask(database).execute(movie);
            new UpdateIsFavouriteTask(database).execute(movie.getmMovieId());
            finish();

        }

    }
}
