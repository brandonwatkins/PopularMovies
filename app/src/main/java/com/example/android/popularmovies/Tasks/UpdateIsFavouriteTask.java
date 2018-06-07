package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Database.PopularMoviesDb;

/**
 * Created by Brandon Watkins
 */

public class UpdateIsFavouriteTask extends AsyncTask<Void, Void, String> {
    private PopularMoviesDb popularMoviesDb;


    public UpdateIsFavouriteTask(PopularMoviesDb popularMoviesDb) {
        this.popularMoviesDb = popularMoviesDb;
    }

    @Override
    protected Void doInBackground(String... id) {
        popularMoviesDb.popularMoviesDao().updateFavourite(id[0]);
        return null;
    }


}
