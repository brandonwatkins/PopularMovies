package com.example.android.popularmovies;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.android.popularmovies.Database.Movie;
import com.example.android.popularmovies.Database.PopularMoviesDb;
import com.example.android.popularmovies.Tasks.AddToFavouritesTask;

import java.util.List;

/**
 * ViewModel for the PopularMovies
 * Created by Brandon Watkins
 */

public class PopularMoviesViewModel extends AndroidViewModel {

    private static final String LOG_TAG = PopularMoviesViewModel.class.getSimpleName();
    /*
    * This linked helped me implement the View Model
    * https://medium.com/google-developers/viewmodels-a-simple-example-ed5ac416317e
    */

    private final LiveData<List<Movie>> favouritesList;

    private PopularMoviesDb database;

    public PopularMoviesViewModel(Application application) {
        super(application);

        database = PopularMoviesDb.getDatabase(application);

        favouritesList = database.popularMoviesDao().getFavourites();
    }

    public LiveData<List<Movie>> getFavouritesList() {
        return favouritesList;
    }

    void addMovie(Movie m) {
        Log.d(LOG_TAG, "Adding movie to favourites");
        new AddToFavouritesTask(database).execute(m);
    }
}
