package com.example.android.popularmovies;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String MOVIE_KEY = "movie_key";
    private static final String POPULAR_KEY = "popular";
    private static final String TOP_RATED_KEY = "top_rated";

    private GridView gridView;
    private MovieAdapter mMovieAdapter;
    public ArrayList<Movie> movies = new ArrayList<Movie>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);
        mMovieAdapter = new MovieAdapter(this);

        getMostPopular();

        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Movie movie = movies.get(position);

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(MOVIE_KEY, movie);



                // This tells the GridView to redraw itself
                // in turn calling your MovieAdapter's getView method again for each cell
               // movieAdapter.notifyDataSetChanged();
            }

        });

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

}
