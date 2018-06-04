package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Adapters.TrailerAdapter;
import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.Adapters.MovieAdapter;
import com.example.android.popularmovies.Utils.JSONUtils;
import com.example.android.popularmovies.Utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveTrailersTask extends AsyncTask<String, Void, ArrayList> {

    MovieAdapter mTrailerAdapter;

    public RetrieveTrailersTask(TrailerAdapter trailerAdapter) {
        this.mTrailerAdapter = trailerAdapter;
    }

    @Override
    protected List<Movie> doInBackground(String... params) {

        List<Movie> movieArray = new ArrayList<>();
        JSONUtils jsonUtils = new JSONUtils();

        try {
            //Use the parameter passed in (either top_rated or popular) to retrieve the correct
            //list of movies
            String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl(params[0]));
            movieArray = jsonUtils.parseMoviesJSON(responseFromHttpUrl);
        } catch (IOException o) {
            o.printStackTrace();
        }

        return movieArray;
    }

    @Override
    protected void onPostExecute(ArrayList trailers) {
        //Call deliver results method return the results of the task
        mTrailerAdapter.deliverResults(trailers);

    }
}