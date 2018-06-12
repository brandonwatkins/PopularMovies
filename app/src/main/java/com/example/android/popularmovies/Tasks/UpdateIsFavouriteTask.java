package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;

/**
 * Created by Brandon Watkins
 */

public class UpdateIsFavouriteTask extends AsyncTask<Integer, Void, Void> {
    private PopularMoviesDb popularMoviesDb;


    public UpdateIsFavouriteTask(PopularMoviesDb popularMoviesDb) {
        this.popularMoviesDb = popularMoviesDb;
    }

    @Override
    protected Void doInBackground(Integer... id) {
        Movie m = popularMoviesDb.popularMoviesDao().getMovieById(id[0]);
        m.setmIsFav(true);
        popularMoviesDb.popularMoviesDao().updateFavourite(m);
        Log.d("UpdateIsFavTask", "SET FAVOURITE");
        return null;
    }


}
