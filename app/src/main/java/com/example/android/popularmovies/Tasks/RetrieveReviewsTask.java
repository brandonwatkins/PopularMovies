package com.example.android.popularmovies.Tasks;

import android.os.AsyncTask;

import com.example.android.popularmovies.Adapters.ReviewsAdapter;
import com.example.android.popularmovies.Utils.JSONUtils;
import com.example.android.popularmovies.Utils.NetworkUtils;

import java.io.IOException;
import java.util.ArrayList;

public class RetrieveReviewsTask extends AsyncTask<Integer, Void, ArrayList<String>> {

    ReviewsAdapter mReviewsAdapter;

    public RetrieveReviewsTask(ReviewsAdapter reviewsAdapter) {
        this.mReviewsAdapter = reviewsAdapter;
    }

    @Override
    protected ArrayList<String> doInBackground(Integer... params) {

        ArrayList<String> reviewArray = new ArrayList<>();
        JSONUtils jsonUtils = new JSONUtils();

        try {
            //Use the id passed in to retrieve the correct movie reviews
            String responseFromHttpUrl = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildReviewsUrl(Integer.toString(params[0])));
            reviewArray = jsonUtils.parseReviewsJSON(responseFromHttpUrl);
        } catch (IOException o) {
            o.printStackTrace();
        }

        return reviewArray;
    }

    @Override
    protected void onPostExecute(ArrayList<String> reviews) {
        // Call deliver results method return the results of the task
        mReviewsAdapter.deliverResults(reviews);

    }
}