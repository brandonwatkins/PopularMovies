package com.example.android.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieAdapter extends BaseAdapter {

    private static final String LOG_TAG = MovieAdapter.class.getSimpleName();

    private Context mContext;
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movie) {
        this.mContext = context;
        this.mMovies = movie;
    }

    @Override
    public int getCount() {
       return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Help from: https://www.androidtutorialpoint.com/networking/android-picasso-tutorial-using-picasso-android-library/
     *
     * @param position
     * @param convertView
     * @param parent
     * @return the converted view
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;

        if (convertView == null) {
            layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.movie_poster, null);
        }

        ImageView imageView = convertView.findViewById(R.id.poster);

        //Picasso is used to load the movie poster url into the image view
        Picasso.with(mContext)
                .load(mMovies.get(position).getmMoviePosterUrl())
                .into(imageView);

        return convertView;
    }

    public void deliverResults(List<Movie> data) {
        //Remove existing data from the ArrayList
        mMovies.clear();
        //Add all the new data passed in into the ArrayList
        mMovies.addAll(data);
        notifyDataSetChanged();
    }

}
