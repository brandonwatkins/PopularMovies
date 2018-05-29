package com.example.android.popularmovies.Utils;

import android.util.Log;

import com.example.android.popularmovies.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONUtils {

    private static final String LOG_TAG = JSONUtils.class.getSimpleName();


    public List<Movie> parseMoviesJSON (String JSONString) {

        try {

            String FALL_BACK_STRING = "N/A";

            List<Movie> movieArrayList = new ArrayList<>();

            //Create the main JSON object from the JSON passed in
            JSONObject mainJSONObject = new JSONObject(JSONString);

            //Create the results array
            JSONArray resultsArray = mainJSONObject.getJSONArray("results");


            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject JSONMovie = resultsArray.getJSONObject(i);

                //Create movie object from JSON
                Movie movie = new Movie();
                movie.setMovieId(JSONMovie.optLong("id"));
                movie.setOriginalTitle(JSONMovie.optString("original_title", FALL_BACK_STRING));
                movie.setMoviePosterUrl("http://image.tmdb.org/t/p/w342/" + JSONMovie.optString("poster_path", FALL_BACK_STRING));
                movie.setPlotSynopsis(JSONMovie.optString("overview", FALL_BACK_STRING));
                movie.setUserRating(JSONMovie.optDouble("vote_average", 0));
                movie.setReleaseDate(JSONMovie.optString("release_date", FALL_BACK_STRING));

                //Add the movie object to my List
                movieArrayList.add(movie);
            }

            return movieArrayList;

        } catch (JSONException j) {
            j.printStackTrace();
            Log.d(LOG_TAG, "JSON Exception error");
        }

        return null;
    }
}


