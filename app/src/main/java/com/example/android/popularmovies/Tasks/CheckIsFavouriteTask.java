package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;
import com.example.android.popularmovies.isInDatabaseResponse;

/**
 * Created by Brandon Watkins
 */

public class CheckIsFavouriteTask extends AsyncTask<Integer, Void, Boolean> {
    private PopularMoviesDb popularMoviesDb;

    public isInDatabaseResponse delegate = null;


    public CheckIsFavouriteTask(PopularMoviesDb popularMoviesDb) {
        this.popularMoviesDb = popularMoviesDb;
    }

    @Override
    protected Boolean doInBackground(Integer... id) {
        Movie m = popularMoviesDb.popularMoviesDao().getMovieById(id[0]);
        try {
            if (m.getmIsFav() == true) {
                return true;
            }
        } catch (NullPointerException e) {
                return false;
        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean isFav) {
        super.onPostExecute(isFav);
        delegate.processFinish(isFav);
    }
}
