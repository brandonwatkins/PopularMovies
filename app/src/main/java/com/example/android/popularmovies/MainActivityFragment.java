package com.example.android.popularmovies;

import android.view.View;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivityFragment {

    public ArrayList<Movie> movies = new ArrayList<Movie>();
    public MainActivityFragment mainActivityFragment;
    GridView gridView;

    public MainActivityFragment() {
        mainActivityFragment = this;
    }

}
