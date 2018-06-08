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

    //@Query("SELECT * FROM StudySession ORDER BY time")
    //LiveData<List<StudySession>> getAllStudySessions();

    @Query("SELECT * FROM PopularMovies WHERE mIsFav!=1 ORDER BY mMovieId")
    LiveData<List<Movie>> getFavourites();

    @Insert(onConflict = REPLACE)
    void addFavourite(Movie movie);

    @Update
    void updateFavourite(Movie movie);

    @Query("SELECT * FROM PopularMovies WHERE mMovieId = :id")
    Movie getMovieById(int id);

    /*@Query("SELECT SUM(sessionLength) FROM StudySession WHERE sent=0")
    long getTotalWeeklyHours();

    @Query("DELETE FROM StudySession WHERE sessionLength=0")
    void purgeInvalidSessions();*/

    @Delete
    void removeFavourite(Movie movie);

    @Query("UPDATE PopularMovies SET mIsFav=1 WHERE mMovieId = :id")
    void updateDBFavourite(String id);
}
