package com.framgia.hien.moviedb.data.source.remote.service;

import com.framgia.hien.moviedb.data.model.CastResponse;
import com.framgia.hien.moviedb.data.model.Company;
import com.framgia.hien.moviedb.data.model.GenresResponse;
import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.data.model.MovieByPerson;
import com.framgia.hien.moviedb.data.model.MovieResponse;
import com.framgia.hien.moviedb.data.model.Person;
import com.framgia.hien.moviedb.data.model.TrailerResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    @GET("movie/popular")
    Single<MovieResponse> getMoviePopular(@Query("api_key") String key, @Query("page") int pageNumber);

    @GET("movie/now_playing")
    Single<MovieResponse> getMovieNowPlaying(@Query("api_key") String key, @Query("page") int pageNumber);

    @GET("movie/upcoming")
    Single<MovieResponse> getMovieUpcoming(@Query("api_key") String key, @Query("page") int pageNumber);

    @GET("movie/top_rated")
    Single<MovieResponse> getMovieTopRated(@Query("api_key") String key, @Query("page") int pageNumber);

    @GET("genre/movie/list")
    Single<GenresResponse> getGenres(@Query("api_key") String key);

    @GET("movie/{movie_id}")
    Single<Movie> getDetailMovie(@Path("movie_id") int id, @Query("api_key") String key);

    @GET("movie/{movie_id}/credits")
    Single<CastResponse> getCastOfMovie(@Path("movie_id") int id, @Query("api_key") String key);

    @GET("movie/{movie_id}/videos")
    Single<TrailerResponse> getTrailer(@Path("movie_id") int id, @Query("api_key") String key);

    @GET("company/{company_id}")
    Single<Company> getCompany(@Path("company_id") int companyId, @Query("api_key") String key);

    @GET("person/{person_id}")
    Single<Person> getPerson(@Path("person_id") int personId, @Query("api_key") String key);

    @GET("search/person")
    Single<MovieByPerson> getMovieByPerson(@Query("api_key") String key, @Query("language") String language
            , @Query("query") String querry);

    @GET("company/{company_id}/movies")
    Single<MovieResponse> getMovieByCompany(@Path("company_id") int companyId, @Query("api_key") String key,
                                            @Query("language") String language);

    @GET("discover/movie")
    Single<MovieResponse> searchMovieByGenre(@Query("api_key") String key, @Query("with_genres") int genreId
            , @Query("page") int page);

    @GET("search/movie")
    Single<MovieResponse> searchMovieByName(@Query("api_key") String key, @Query("query") String querry
            , @Query("page") int page);
}

