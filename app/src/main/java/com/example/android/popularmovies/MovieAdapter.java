package com.example.android.popularmovies;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
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

        if ( mMovies.size() != 0 ) {
            int size = this.mMovies.size();
            Log.d(LOG_TAG, "getCount() : " + String.valueOf(size));
            return size; // >> 20
        } else {
            return 0;
        }
       //return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int position) {
       // return mMovies.get(position).getId();
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;

        if (convertView == null) {
            layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.movie_poster, null);
        }

        ImageView imageView = convertView.findViewById(R.id.poster);

        Picasso.with(mContext)
                .load(mMovies.get(position).getMoviePosterUrl())
                .into(imageView);

        return convertView;
    }

    public void deliverResults(List<Movie> data) {
        this.mMovies = data;
        this.notifyDataSetChanged();
    }

}
