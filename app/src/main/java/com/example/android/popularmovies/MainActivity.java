package com.example.android.popularmovies;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    //Keys passed into the RetrieveMovies Task
    private static final String POPULAR_KEY = "popular";
    private static final String TOP_RATED_KEY = "top_rated";

    //private GridView gridView;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mRecyclerViewAdapter;
    private MovieAdapter mMovieAdapter;
    private TextView mEmptyView;
    public ArrayList<Movie> mMovies;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConnectivityManager cm =
                (ConnectivityManager) MainActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        mMovies = new ArrayList<>();
        mEmptyView = findViewById(R.id.tvEmpty);

        //gridView = findViewById(R.id.gridView);
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));

        mRecyclerViewAdapter = new RecyclerViewAdapter(this, mMovies);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        //mMovieAdapter = new MovieAdapter(this, mMovies);

        //By default load most popular on start up
        getMostPopular();

        //Show "empty" TextView if mMovies didn't get filled
        if (mMovies.isEmpty()) {
            mRecyclerView.setVisibility(View.GONE);
            mEmptyView.setVisibility(View.VISIBLE);
        }
        else {
            mRecyclerView.setVisibility(View.VISIBLE);
            mEmptyView.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //Load the correct list depending on the users choice
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
        //Start the AsyncTask that returns the top rated list
        if (isConnected) {
            RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mRecyclerViewAdapter);
            retrieveMoviesTask.execute(TOP_RATED_KEY);
        } else {
            Toast errorToast = Toast.makeText(this, "Not connected to internet", Toast.LENGTH_LONG);
            errorToast.show();
        }
    }

    private void getMostPopular() {
        //Start the AsyncTask that returns the most popular list
        if (isConnected) {
            RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mRecyclerViewAdapter);
            retrieveMoviesTask.execute(POPULAR_KEY);
        } else {
            Toast errorToast = Toast.makeText(this, "Not connected to internet", Toast.LENGTH_LONG);
            errorToast.show();
        }

    }

}
