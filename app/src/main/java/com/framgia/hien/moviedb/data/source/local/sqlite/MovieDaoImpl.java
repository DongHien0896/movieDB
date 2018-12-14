package com.framgia.hien.moviedb.data.source.local.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.framgia.hien.moviedb.data.model.Movie;
import com.framgia.hien.moviedb.util.Constants;
import com.framgia.hien.moviedb.util.common.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class MovieDaoImpl implements MovieDao {

    private static MovieDaoImpl sInstance;
    private DbHelper mDbHelper;

    private MovieDaoImpl(DbHelper helper) {
        mDbHelper = helper;
    }

    public static synchronized MovieDaoImpl getInstance(DbHelper helper) {
        if (sInstance == null) {
            sInstance = new MovieDaoImpl(helper);
        }
        return sInstance;
    }

    @Override
    public Movie getMovie(int movieId) {
        Movie movie = null;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        final String selection =
                StringUtil.genString(DbHelper.MovieEntry.COLUMN_NAME_ID, Constants.COMA_QUESTION);
        final String[] selectionArgs = {String.valueOf(movieId)};
        Cursor cursor = db.query(
                DbHelper.MovieEntry.TABLE_NAME,
                genProjection(),
                selection,
                selectionArgs,
                null,
                null,
                null
        );
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            movie = genMovie(cursor);
        }
        closeCursor(cursor);
        closeDb(db);
        return movie;
    }

    @Override
    public List<Movie> getMovies() {
        List<Movie> movies;
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                DbHelper.MovieEntry.TABLE_NAME,
                genProjection(),
                null,
                null,
                null,
                null,
                null
        );
        movies = genMovies(cursor);
        closeDb(db);
        return movies;
    }

    @Override
    public void insertMovie(Movie movie) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbHelper.MovieEntry.COLUMN_NAME_ID, movie.getId());
        values.put(DbHelper.MovieEntry.COLUMN_NAME_TITLE, movie.getTitle());
        values.put(DbHelper.MovieEntry.COLUMN_NAME_RELEASE_DATE, movie.getReleaseDate());
        values.put(DbHelper.MovieEntry.COLUMN_NAME_VOTE_AVERAGE, movie.getVoteAverage());
        values.put(DbHelper.MovieEntry.COLUMN_NAME_POSTER_PATH, movie.getPosterPath());
        db.insert(DbHelper.MovieEntry.TABLE_NAME, null, values);
        closeDb(db);
    }

    @Override
    public void deleteMovie(Movie movie) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        final String selection =
                StringUtil.genString(DbHelper.MovieEntry.COLUMN_NAME_ID, Constants.COMA_QUESTION);
        final String[] selectionArgs = {String.valueOf(movie.getId())};
        db.delete(DbHelper.MovieEntry.TABLE_NAME, selection, selectionArgs);
        closeDb(db);
    }

    private void closeDb(SQLiteDatabase db) {
        db.close();
    }

    private void closeCursor(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.isClosed();
        }
    }

    private List<Movie> genMovies(Cursor cursor) {
        List<Movie> movies = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                movies.add(genMovie(cursor));
            } while (cursor.moveToNext());
        }
        closeCursor(cursor);
        return movies;
    }

    private Movie genMovie(Cursor cursor) {
        Movie.Builder builder = new Movie.Builder();
        Movie movie = builder
                .setId(cursor.getInt(cursor.getColumnIndex(DbHelper.MovieEntry.COLUMN_NAME_ID)))
                .setTitle(cursor.getString(
                        cursor.getColumnIndex(DbHelper.MovieEntry.COLUMN_NAME_TITLE)))
                .setReleaseDate(cursor.getString(
                        cursor.getColumnIndex(DbHelper.MovieEntry.COLUMN_NAME_RELEASE_DATE)))
                .setVoteAverage(cursor.getFloat(
                        cursor.getColumnIndex((DbHelper.MovieEntry.COLUMN_NAME_VOTE_AVERAGE))))
                .setPosterPath(cursor.getString(
                        cursor.getColumnIndex(DbHelper.MovieEntry.COLUMN_NAME_POSTER_PATH)))
                .build();
        return movie;
    }

    private String[] genProjection() {
        return new String[]{
                DbHelper.MovieEntry.COLUMN_NAME_ID,
                DbHelper.MovieEntry.COLUMN_NAME_TITLE,
                DbHelper.MovieEntry.COLUMN_NAME_RELEASE_DATE,
                DbHelper.MovieEntry.COLUMN_NAME_VOTE_AVERAGE,
                DbHelper.MovieEntry.COLUMN_NAME_POSTER_PATH
        };
    }
}
