package com.framgia.hien.moviedb.util;

public class Constants {
    public static final int HOME_SLIDER_TOTAL_PAGE = 3;
    public static final String END_POINT_URL = "https://api.themoviedb.org/3/";
    public static final String END_POINT_IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    public static final String COMA_QUESTION = " = ?";
    public static final int PAGE_REQUEST = 1;
    public static final String TYPE_POPULAR = "popular";
    public static final String TYPE_NOW_PLAYING = "now playing";
    public static final String TYPE_UPCOMING = "upcoming";
    public static final String TYPE_TOP_RATED = "top rated";
    public static final String ARGUMENT_MOVIE_ID = "movie_id";
    public static final int DEFAULT_VALUE = 0;
    public static final String EXAMPLE_URL = "/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg";
    public static final String MOVIE = "com.framgia.hien.moviedb.intent.movie";
    public static final String MESSAGE_ERROR_PLAY_TRAILER = "Trailer is not variable";
    public static final String PERSON = "com.framgia.hien.moviedb.intent.person";
    public static final String COMPANY = "com.framgia.hien.moviedb.intent.company";
    public static final String LANGUAGE = "en-US";
    public static final int VALUE_INTEGER_NULL = 1;
    public static final String MESSAGE_ERROR = "Error";
    public static final String MESSAGE_ADD_FAVORITE = "Added to favorite!";
    public static final String MESSAGE_REMOVE_FAVORITE = "Removed to favorite!";

    private Constants() {
        // No-op
    }
}
