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

    TrailerAdapter mTrailerAdapter;

    public RetrieveTrailersTask(TrailerAdapter trailerAdapter) {
        this.mTrailerAdapter = trailerAdapter;
    }

    @Override
    protected ArrayList doInBackground(String... params) {

        ArrayList trailerArray = new ArrayList<>();
        JSONUtils jsonUtils = new JSONUtils();

        try {
            //Use the id passed in to retrieve the correct movie trailers
            String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildTrailerlUrl(params[0]));
            trailerArray = jsonUtils.parseTrailersJSON(responseFromHttpUrl);
        } catch (IOException o) {
            o.printStackTrace();
        }

        return trailerArray;
    }

    @Override
    protected void onPostExecute(ArrayList trailers) {
        //Call deliver results method return the results of the task
        mTrailerAdapter.deliverResults(trailers);

    }
}