package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.Utils.JSONUtils;
import com.example.android.popularmovies.Utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HighestRatedMoviesTask extends AsyncTask<String, Object, List<Movie>> {
   /* @Override
    protected void onPreExecute() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        loadingSpinner.setVisibility(View.VISIBLE);
    }*/

    @Override
    protected List<Movie> doInBackground(String... params) {

        List<Movie> moviesFromJson = new ArrayList<>();
        try {
            String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildQueryUrl(params[0]));
            moviesFromJson = JSONUtils.getMoviesFromResponse(responseFromHttpUrl);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return moviesFromJson;
    }

    @Override
    protected void onPostExecute(List<Movie> movies) {
        adapter.setMovieList(movies);
        Log.v(TAG, "Set new adapter values");
    }
}