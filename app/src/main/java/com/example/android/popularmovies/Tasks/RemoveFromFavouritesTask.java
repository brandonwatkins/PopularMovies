package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;

/**
 * Created by Brandon Watkins
 */

public class RemoveFromFavouritesTask extends AsyncTask<Movie, Void, Void> {
    private PopularMoviesDb popularMoviesDb;

    public RemoveFromFavouritesTask(PopularMoviesDb popularMoviesDb) {
        this.popularMoviesDb = popularMoviesDb;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        popularMoviesDb.popularMoviesDao().removeFavourite(movies[0]);
        return null;
    }


}
