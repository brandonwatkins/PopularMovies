package com.example.android.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView mGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGridView = (GridView)findViewById(R.id.gridView);
        final movieUtils movieUtils = new movieUtils(this, movies);
        mGridView.setAdapter(movieUtils);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, android.view.View view, int position, long id) {
                Movie movie = movies[position];
                //movie.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your MovieAdapter's getView method again for each cell
                //movieUtils.notifyDataSetChanged();
            }
        });
    }
}
