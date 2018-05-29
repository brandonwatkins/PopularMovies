package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.MovieAdapter;
import com.example.android.popularmovies.Utils.JSONUtils;
import com.example.android.popularmovies.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveMoviesTask extends AsyncTask<String, Object, List<Movie>> {
   /* @Override
    protected void onPreExecute() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        loadingSpinner.setVisibility(View.VISIBLE);
    }*/

    MovieAdapter mMovieAdapter;
    public RetrieveMoviesTask(MovieAdapter moviesAdapter) {
        this.mMovieAdapter = moviesAdapter;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {

        List<Movie> movieArray = new ArrayList<>();
        JSONUtils jsonUtils = new JSONUtils();

        try {
            String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl(params[0]));
            movieArray = jsonUtils.parseMoviesJSON(responseFromHttpUrl);
        } catch (IOException o) {
            o.printStackTrace();
        }

        return movieArray;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        mMovieAdapter.deliverResults(movies);
        Log.v("RetrieveTASK", "Set new adapter values");
    }
}