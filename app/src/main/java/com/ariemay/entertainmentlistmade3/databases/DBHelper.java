package com.ariemay.entertainmentlistmade3.databases;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbmovieapp";
    private static final int DATABASE_VERSION = 2;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DBContract.TABLE_MOVIE,
            DBContract.MovieColumns._ID,
            DBContract.MovieColumns.ID_MOVIE,
            DBContract.MovieColumns.TITLE_MOVIE,
            DBContract.MovieColumns.VOTE_AVERAGE_MOVIE,
            DBContract.MovieColumns.OVERVIEW_MOVIE,
            DBContract.MovieColumns.RELEASE_DATE_MOVIE,
            DBContract.MovieColumns.POSTER_PATH_MOVIE,
            DBContract.MovieColumns.BACKDROP_PATH_MOVIE
    );
    private static final String SQL_CREATE_TABLE_TV = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DBContract.TABLE_TV,
            DBContract.TvColumns._ID,
            DBContract.TvColumns.ID_TV,
            DBContract.TvColumns.NAME_TV,
            DBContract.TvColumns.FIRST_AIR_DATE_TV,
            DBContract.TvColumns.VOTE_AVERAGE_TV,
            DBContract.TvColumns.OVERVIEW_TV,
            DBContract.TvColumns.POSTER_PATH_TV,
            DBContract.TvColumns.BACKDROP_PATH_TV
    );

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
        db.execSQL(SQL_CREATE_TABLE_TV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.TABLE_MOVIE);
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.TABLE_TV);
        onCreate(db);
    }
}
