package com.example.android.popularmovies;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie class
 */
public class Movie implements Parcelable {

    private Long mMovieId;
    private String mOriginalTitle;
    private String mMoviePosterUrl;
    private String mPlotSynopsis;
    private Double mUserRating;
    private String mReleaseDate;

    public Movie() {}

    public Movie(String name, String imageUrl) {
        this.mOriginalTitle = name;
        this.mMoviePosterUrl = imageUrl;
    }

    public Movie(String title, String posterUrl, String synopsis,
                 Double rating, String date) {
        this.mOriginalTitle = title;
        this.mMoviePosterUrl = posterUrl;
        this.mPlotSynopsis = synopsis;
        this.mUserRating = rating;
        this.mReleaseDate = date;
    }

    private Movie(Parcel in){
        mOriginalTitle = in.readString();
        mMoviePosterUrl = in.readString();
        mPlotSynopsis = in.readString();
        mUserRating = in.readDouble();
        mReleaseDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mOriginalTitle);
        parcel.writeString(mMoviePosterUrl);
        parcel.writeString(mPlotSynopsis);
        parcel.writeDouble(mUserRating);
        parcel.writeString(mReleaseDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }

    };

    public String getReleaseYear(String releaseString) {
        String[] dateArray = releaseString.split("-");
        String releaseYear = dateArray[0];
        return releaseYear;
    }

    public Long getmMovieId() {
        return mMovieId;
    }

    public void setmMovieId(Long mMovieId) {
        this.mMovieId = mMovieId;
    }

    public String getmOriginalTitle() {
        return mOriginalTitle;
    }

    public void setmOriginalTitle(String mOriginalTitle) {
        this.mOriginalTitle = mOriginalTitle;
    }

    public String getmMoviePosterUrl() {
        return mMoviePosterUrl;
    }

    public void setmMoviePosterUrl(String mMoviePosterUrl) {
        this.mMoviePosterUrl = mMoviePosterUrl;
    }

    public String getmPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setmPlotSynopsis(String mPlotSynopsis) {
        this.mPlotSynopsis = mPlotSynopsis;
    }

    public Double getmUserRating() {
        return mUserRating;
    }

    public void setmUserRating(Double mUserRating) {
        this.mUserRating = mUserRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }
}
