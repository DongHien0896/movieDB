package com.framgia.hien.moviedb.data.source.local.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.framgia.hien.moviedb.util.common.StringUtil;

public class DbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "MovieDb.db";
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String REAL_TYPE = " REAL";
    private static final String COMA_SEP = ",";
    private static final String CREATE_TABLE = "CREATE TABLE ";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String OPEN_BRACKET = " (";
    private static final String CLOSE_BRACKET = " )";

    private static DbHelper sInstance;

    public static DbHelper getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new DbHelper(context);
        }
        return sInstance;
    }

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = StringUtil.genString(
                CREATE_TABLE,
                MovieEntry.TABLE_NAME,
                OPEN_BRACKET,
                MovieEntry.COLUMN_NAME_ID,
                INTEGER_TYPE,
                COMA_SEP,
                MovieEntry.COLUMN_NAME_TITLE,
                TEXT_TYPE,
                COMA_SEP,
                MovieEntry.COLUMN_NAME_RELEASE_DATE,
                TEXT_TYPE,
                COMA_SEP,
                MovieEntry.COLUMN_NAME_VOTE_AVERAGE,
                REAL_TYPE,
                COMA_SEP,
                MovieEntry.COLUMN_NAME_POSTER_PATH,
                TEXT_TYPE,
                CLOSE_BRACKET
        );
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String dropTable = StringUtil.genString(
                DROP_TABLE,
                MovieEntry.TABLE_NAME
        );
        db.execSQL(dropTable);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "movie";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_RELEASE_DATE = "release_date";
        public static final String COLUMN_NAME_VOTE_AVERAGE = "vote_average";
        public static final String COLUMN_NAME_POSTER_PATH = "poster_path";
    }
}
