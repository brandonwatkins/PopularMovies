package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.popularmovies.Movie;
import com.example.android.popularmovies.R;
import com.example.android.popularmovies.RecyclerViewAdapter;
import com.example.android.popularmovies.Utils.JSONUtils;
import com.example.android.popularmovies.Utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetrieveMoviesTask extends AsyncTask<String, Object, List<Movie>> {

    RecyclerViewAdapter mRecyclerViewAdapter;

    public RetrieveMoviesTask(RecyclerViewAdapter recyclerViewAdapter) {
        this.mRecyclerViewAdapter = recyclerViewAdapter;
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
    protected void onPostExecute(List<Movie> movies) {
        //Call deliver results method return the results of the task
        mRecyclerViewAdapter.deliverResults(movies);

    }
}