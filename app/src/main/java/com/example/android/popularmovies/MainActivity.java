package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.popularmovies.Tasks.FindMoviesTask;
import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String MOVIE_KEY = "movie_key";
    private static final String POPULAR_KEY = "popular";
    private static final String TOP_RATED_KEY = "top_rated";

    MovieAdapter mMovieAdapter = new MovieAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.rating:
                getHighestRated();
                break;
            case R.id.popularity:
                getMostPopular();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getHighestRated() {
        RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
        Log.d(LOG_TAG, "Fetching Highest Rated");
        retrieveMoviesTask.execute(TOP_RATED_KEY);
        //getSupportActionBar().setTitle(R.string.highest_rated);
    }

    private void getMostPopular() {
        RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
        Log.d(LOG_TAG, "Fetching Popular");
        retrieveMoviesTask.execute(POPULAR_KEY);
        //getSupportActionBar().setTitle(R.string.popular);
    }

    @Override
    public void onClick(Movie movie) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(MOVIE_KEY, movie);
        startActivity(intent);
    }

}
