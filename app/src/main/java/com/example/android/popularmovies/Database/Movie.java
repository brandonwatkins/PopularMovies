package com.example.android.popularmovies.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Movie class
 *
 * When creating the parcel I found help writing and reading a boolean from the link below
 * Source: https://stackoverflow.com/questions/6201311/how-to-read-write-a-boolean-when-implementing-the-parcelable-interface
 */

@Entity(tableName = "PopularMovies")
public class Movie implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public int mMovieId;
    public String mOriginalTitle;
    public String mMoviePosterUrl;
    public String mPlotSynopsis;
    public Double mUserRating;
    public String mReleaseDate;
    public Boolean mIsFav;

    public Movie() {}

    @Ignore
    public Movie(String name, String imageUrl) {
        this.mOriginalTitle = name;
        this.mMoviePosterUrl = imageUrl;
    }

    @Ignore
    public Movie(int id, String title, String posterUrl, String synopsis,
                 Double rating, String date, Boolean fav) {
        this.mMovieId = id;
        this.mOriginalTitle = title;
        this.mMoviePosterUrl = posterUrl;
        this.mPlotSynopsis = synopsis;
        this.mUserRating = rating;
        this.mReleaseDate = date;
        this.mIsFav = fav;
    }

    private Movie(Parcel in){
        mMovieId = in.readInt();
        mOriginalTitle = in.readString();
        mMoviePosterUrl = in.readString();
        mPlotSynopsis = in.readString();
        mUserRating = in.readDouble();
        mReleaseDate = in.readString();
        mIsFav = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mMovieId);
        parcel.writeString(mOriginalTitle);
        parcel.writeString(mMoviePosterUrl);
        parcel.writeString(mPlotSynopsis);
        parcel.writeDouble(mUserRating);
        parcel.writeString(mReleaseDate);
        parcel.writeByte((byte) (mIsFav ? 1 : 0));
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

    public Boolean getmIsFav() {
        return mIsFav;
    }

    public void setmIsFav(Boolean mIsFav) {
        this.mIsFav = mIsFav;
    }

    public int getmMovieId() {
        return mMovieId;
    }

    public void setmMovieId(int mMovieId) {
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
