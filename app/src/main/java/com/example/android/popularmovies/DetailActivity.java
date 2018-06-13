package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Adapters.ReviewsAdapter;
import com.example.android.popularmovies.Adapters.TrailerAdapter;
import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;
import com.example.android.popularmovies.Tasks.AddToFavouritesTask;
import com.example.android.popularmovies.Tasks.CheckIsFavouriteTask;
import com.example.android.popularmovies.Tasks.RemoveFromFavouritesTask;
import com.example.android.popularmovies.Tasks.RetrieveReviewsTask;
import com.example.android.popularmovies.Tasks.RetrieveTrailersTask;
import com.example.android.popularmovies.Tasks.UpdateIsFavouriteTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements isInDatabaseResponse {

    // Key for movie object that is passed in
    private static final String MOVIE_KEY = "movie_key";

    private TextView mOriginalTitle;
    private TextView mReleaseDate;
    private TextView mPlotSynopsis;
    private TextView mUserRating;
    private ImageView mPoster;
    private String mPosterURL;
    private ImageButton mFavBtn;
    private ImageButton mUnFavBtn;

    private TrailerAdapter mTrailerAdapter;
    private RecyclerView mTrailersRecyclerView;

    private ReviewsAdapter mReviewsAdapter;
    private RecyclerView mReviewsRecyclerView;

    // App database
    PopularMoviesDb database;

    private boolean isInFavDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Get reference to the apps database
        database = PopularMoviesDb.getDatabase(this);

        mOriginalTitle = findViewById(R.id.tvOriginalTitle);
        mReleaseDate = findViewById(R.id.tvReleaseDate);
        mPlotSynopsis = findViewById(R.id.tvPlotSynopsis);
        mUserRating = findViewById(R.id.tvUserRating);
        mPoster = findViewById(R.id.ivPoster);
        mTrailersRecyclerView = findViewById(R.id.rvTrailers);
        mReviewsRecyclerView = findViewById(R.id.rvReviews);
        mFavBtn = findViewById(R.id.ibFavButton);
        mUnFavBtn = findViewById(R.id.ibUnFavButton);

        // ArrayLists that hold all the various Reviews and Trailers
        ArrayList<String> mTrailers = new ArrayList<>();
        ArrayList<String> mReviews = new ArrayList<>();

        // Retrieve the movie object that was passed in
        Intent i = getIntent();
        Movie movie = i.getParcelableExtra(MOVIE_KEY);

        int id = movie.getmMovieId();

        checkIfMovieIsFav(id);

        String outOfTen = getString(R.string.out_of_ten);

        mOriginalTitle.setText(movie.getmOriginalTitle());
        mReleaseDate.setText(movie.getReleaseYear(movie.getmReleaseDate()));
        mPlotSynopsis.setText(movie.getmPlotSynopsis());
        mUserRating.setText(String.valueOf(movie.getmUserRating()) + outOfTen);
        mPosterURL = movie.getmMoviePosterUrl();

        // Load the movies poster again using Picasso
        Picasso.with(DetailActivity.this)
                .load(mPosterURL)
                .into(mPoster);


        // Set up RecyclerViews
        mTrailersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTrailerAdapter = new TrailerAdapter(this, mTrailers);
        mTrailersRecyclerView.setAdapter(mTrailerAdapter);

        mReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mReviewsAdapter = new ReviewsAdapter(this, mReviews);
        mReviewsRecyclerView.setAdapter(mReviewsAdapter);

        // Methods to retrieve the Reviews and Trailers
        retrieveReviews(id);
        retrieveTrailers(id);

        // Set the onClickListeners for each button
        mFavBtn.setOnClickListener(new FavouritesOnClickListener(movie));
        mUnFavBtn.setOnClickListener(new UnFavouritesOnClickListener(movie));

    }

    private void retrieveReviews(int id) {
        RetrieveReviewsTask retrieveReviewsTask = new RetrieveReviewsTask(mReviewsAdapter);
        retrieveReviewsTask.execute(id);
    }

    private void retrieveTrailers(int id) {
        RetrieveTrailersTask retrieveTrailersTask = new RetrieveTrailersTask(mTrailerAdapter);
        retrieveTrailersTask.execute(id);
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
            Toast addToast = Toast.makeText(DetailActivity.this, getString(R.string.add_toast), Toast.LENGTH_LONG);
            addToast.show();
            finish();

        }

    }

    public class UnFavouritesOnClickListener implements View.OnClickListener {

        Movie movie;
        public UnFavouritesOnClickListener(Movie movie) {
            this.movie = movie;
        }


        @Override
        public void onClick(View v) {
            new RemoveFromFavouritesTask(database).execute(movie);
            Toast removeToast = Toast.makeText(DetailActivity.this, getString(R.string.remove_toast), Toast.LENGTH_LONG);
            removeToast.show();
            finish();

        }

    }

    @Override
    public void processFinish(Boolean output) {
        isInFavDatabase = output;
        showCorrectButton();
    }

    public void checkIfMovieIsFav(int id) {
        CheckIsFavouriteTask checkIsFavouriteTask = new CheckIsFavouriteTask(database);

        checkIsFavouriteTask.delegate = this;
        checkIsFavouriteTask.execute(id);
    }

    public void showCorrectButton() {
        if (isInFavDatabase) {
            mFavBtn.setVisibility(View.GONE);
            mUnFavBtn.setVisibility(View.VISIBLE);
        } else {
            mFavBtn.setVisibility(View.VISIBLE);
            mUnFavBtn.setVisibility(View.GONE);
        }
    }

}
