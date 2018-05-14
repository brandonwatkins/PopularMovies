package com.example.android.popularmovies;

public class Movie {

    private String title;
    private String url;
    private String synopsis;
    private String releaseDate;
    private int rating;

    public Movie(String movieUrl, String movieTitle, String movieSynopsis, String movieReleaseDate) {
        this.url = movieUrl;
        this.title = movieTitle;
        this.synopsis = movieSynopsis;
        this.releaseDate = movieReleaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
