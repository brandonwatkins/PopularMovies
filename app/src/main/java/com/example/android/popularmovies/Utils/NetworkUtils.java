package com.example.android.popularmovies.Utils;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * These utilities will be used to communicate with the MovieDB.
 */
public final class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //Base movie URL that I append other information onto
    private static final String MOVIE_URL = "http://api.themoviedb.org/3/movie";

    //API key that needs to be replaced by
    // TODO Reviewer please add API key below
    private static final String API_KEY = "";

    final static String API_PARAM = "api_key";

    /**
     * Method is from Sunshine's NetworkUtils class.
     *
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    /**
     * Builds the URL used to talk to the MovieDB server using a query string. This query string
     * selected by the user when they decide what list of movies to display.
     *
     * @param sortQuery The way the movies will be sorted.
     * @return The URL to use to query the MovieDB server.
     */
    public static URL buildUrl(String sortQuery) {
        Uri builtUri = Uri.parse(MOVIE_URL).buildUpon()
                .appendPath(sortQuery)
                .appendQueryParameter(API_PARAM, API_KEY)
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(LOG_TAG, "Built URI " + url);

        return url;
    }

}