package com.example.android.popularmovies.Database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Brandon Watkins
 */

@Dao
public interface PopularMoviesDao {

    @Query("SELECT * FROM PopularMovies WHERE mIsFav=1 ORDER BY mMovieId")
    LiveData<List<Movie>> getFavourites();

    @Insert(onConflict = REPLACE)
    void addFavourite(Movie movie);

    @Update
    void updateFavourite(Movie movie);

    @Query("SELECT * FROM PopularMovies WHERE mMovieId = :id")
    Movie getMovieById(int id);


    @Delete
    void removeFavourite(Movie movie);

    @Query("UPDATE PopularMovies SET mIsFav=1 WHERE mMovieId = :id")
    void updateDBFavourite(String id);
}
