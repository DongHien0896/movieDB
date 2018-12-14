package com.framgia.hien.moviedb.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {

    @SerializedName("vote_count")
    @Expose
    private int voteCount;
    @SerializedName("id")
    @Expose
    private int mId;
    @SerializedName("video")
    @Expose
    private boolean mVideo;
    @SerializedName("vote_average")
    @Expose
    private float mVoteAverage;
    @SerializedName("title")
    @Expose
    private String mTitle;
    @SerializedName("popularity")
    @Expose
    private Double mPopularity;
    @SerializedName("poster_path")
    @Expose
    private String mPosterPath;
    @SerializedName("original_language")
    @Expose
    private String mOriginalLanguage;
    @SerializedName("original_title")
    @Expose
    private String mOriginalTitle;
    @SerializedName("genre_ids")
    @Expose
    private List<Integer> mGenreIds = null;
    @SerializedName("backdrop_path")
    @Expose
    private String mBackdropPath;
    @SerializedName("adult")
    @Expose
    private Boolean mAdult;
    @SerializedName("overview")
    @Expose
    private String mOverview;
    @SerializedName("release_date")
    @Expose
    private String mReleaseDate;
    @SerializedName("production_companies")
    @Expose
    private List<Company> mCompanies = null;

    private Movie() {
    }

    private Movie(Builder builder) {
        mId = builder.mId;
        mTitle = builder.mTitle;
        mVoteAverage = builder.mVoteAverage;
        mPosterPath = builder.mPosterPath;
        mBackdropPath = builder.mBackdropPath;
        mOverview = builder.mOverview;
        mReleaseDate = builder.mReleaseDate;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public int getId() {
        return mId;
    }

    public void setId(Integer id) {
        this.mId = id;
    }

    public boolean getVideo() {
        return mVideo;
    }

    public void setVideo(boolean video) {
        this.mVideo = video;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.mVoteAverage = voteAverage;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public double getPopularity() {
        return mPopularity;
    }

    public void setPopularity(double popularity) {
        this.mPopularity = popularity;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public void setPosterPath(String posterPath) {
        this.mPosterPath = posterPath;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.mOriginalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.mOriginalTitle = originalTitle;
    }

    public List<Integer> getGenreIds() {
        return mGenreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.mGenreIds = genreIds;
    }

    public String getBackdropPath() {
        return mBackdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.mBackdropPath = backdropPath;
    }

    public boolean getAdult() {
        return mAdult;
    }

    public void setAdult(Boolean adult) {
        this.mAdult = adult;
    }

    public String getOverview() {
        return mOverview;
    }

    public void setOverview(String overview) {
        this.mOverview = overview;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public List<Company> getCompanies() {
        return mCompanies;
    }

    public void setCompanies(List<Company> companies) {
        this.mCompanies = companies;
    }

    public static class Builder {

        private int mId;
        private String mTitle;
        private float mVoteAverage;
        private String mPosterPath;
        private String mBackdropPath;
        private String mOverview;
        private String mReleaseDate;

        public Builder() {

        }

        public Movie build() {
            return new Movie(this);
        }

        public Builder setId(int id) {
            mId = id;
            return this;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setVoteAverage(float voteAverage) {
            mVoteAverage = voteAverage;
            return this;
        }

        public Builder setPosterPath(String posterPath) {
            mPosterPath = posterPath;
            return this;
        }

        public Builder setBackdropPath(String backdropPath) {
            mBackdropPath = backdropPath;
            return this;
        }

        public Builder setOverview(String overview) {
            mOverview = overview;
            return this;
        }

        public Builder setReleaseDate(String releaseDate) {
            mReleaseDate = releaseDate;
            return this;
        }
    }
}
