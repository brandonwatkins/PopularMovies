package com.example.android.popularmovies.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

/**
 * Created by Brandon Watkins
 */

@Database(entities={Movie.class}, version = 1, exportSchema = false)
public abstract class PopularMoviesDb extends RoomDatabase {

    private static PopularMoviesDb INSTANCE;
    private static final String LOG_TAG = PopularMoviesDb.class.getSimpleName();
    private final static Object LOCK = new Object();
    private static final String DATABASE_NAME = "popularMovies_db";

    public static PopularMoviesDb getDatabase(Context context){
        if (INSTANCE == null){
            synchronized (LOCK) {
                Log.d(LOG_TAG, "Creating new database");
                INSTANCE =
                        Room.databaseBuilder(context.getApplicationContext(),
                                PopularMoviesDb.class, DATABASE_NAME)
                                .build();
            }
        }

        Log.d(LOG_TAG, "Getting the database");
        return INSTANCE;
    }

    public static void destroyInstance(){
        INSTANCE = null;
    }

    public abstract PopularMoviesDao popularMoviesDao();
}
