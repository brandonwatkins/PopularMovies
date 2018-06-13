package com.example.android.popularmovies;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Adapters.MovieAdapter;
import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    // Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    // Keys passed into the RetrieveMovies Task
    private static final String POPULAR_KEY = "popular";
    private static final String TOP_RATED_KEY = "top_rated";

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;
    private PopularMoviesViewModel popularMoviesViewModel;
    private TextView mEmptyView;
    public ArrayList<Movie> mMovies;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Code used to find out if the device has an internet connection.
        // Source: https://developer.android.com/training/monitoring-device-state/connectivity-monitoring#java
        ConnectivityManager cm =
                (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mMovies = new ArrayList<>();
        mEmptyView = findViewById(R.id.tvEmpty);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        mMovieAdapter = new MovieAdapter(this, mMovies);
        mRecyclerView.setAdapter(mMovieAdapter);

        // By default load most popular on start up
        getMostPopular();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // Load the correct list depending on the users choice
        switch (id) {
            case R.id.rating:
                getHighestRated();
                break;
            case R.id.popularity:
                getMostPopular();
                break;
            case R.id.favourites:
                getFavourites();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void getHighestRated() {
        // Start the AsyncTask that returns the top rated list
        if (isConnected) {
            RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
            retrieveMoviesTask.execute(TOP_RATED_KEY);

            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        } else {
            Toast errorToast = Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG);
            errorToast.show();

            // Show "empty" TextView if no internet connection
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);

        }
    }

    private void getMostPopular() {
        // Start the AsyncTask that returns the most popular list
        if (isConnected) {
            RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
            retrieveMoviesTask.execute(POPULAR_KEY);

            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        } else {
            Toast errorToast = Toast.makeText(this, getString(R.string.internet_error), Toast.LENGTH_LONG);
            errorToast.show();

            //Show "empty" TextView if no internet connection
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }

    }

    private void getFavourites() {
        // ViewModel for RoomDb
        popularMoviesViewModel = ViewModelProviders.of(this).get(PopularMoviesViewModel.class);

        popularMoviesViewModel.getFavouritesList().observe(this,
                new Observer<List<Movie>>(){
                    @Override
                    public void onChanged(@Nullable List<Movie> movies){
                        mMovieAdapter.deliverResults(movies);
                    }

                });
    }

}
