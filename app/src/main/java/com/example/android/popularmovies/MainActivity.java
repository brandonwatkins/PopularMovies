package com.example.android.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.popularmovies.Tasks.RetrieveMoviesTask;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    //Key for movie object
    private static final String MOVIE_KEY = "movie_key";

    //Keys passed into the RetrieveMovies Task
    private static final String POPULAR_KEY = "popular";
    private static final String TOP_RATED_KEY = "top_rated";

    private GridView gridView;
    private MovieAdapter mMovieAdapter;
    public ArrayList<Movie> mMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gridView);

        mMovies = new ArrayList<>();
        mMovieAdapter = new MovieAdapter(this, mMovies);

        //By default load most popular on start up
        getMostPopular();

        gridView.setAdapter(mMovieAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Movie movie = mMovies.get(position);

                //Create intent with movie object selected and pass it to DetailActivity
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(MOVIE_KEY, movie);
                startActivity(intent);
            }

        });

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
        RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
        retrieveMoviesTask.execute(TOP_RATED_KEY);
    }

    private void getMostPopular() {
        //Start the AsyncTask that returns the most popular list
        RetrieveMoviesTask retrieveMoviesTask = new RetrieveMoviesTask(mMovieAdapter);
        retrieveMoviesTask.execute(POPULAR_KEY);
    }

}
