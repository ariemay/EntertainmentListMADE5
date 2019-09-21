package com.ariemay.entertainmentlistmade3.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;


import com.ariemay.entertainmentlistmade3.databases.ShowHelper;

import static com.ariemay.entertainmentlistmade3.databases.DBContract.AUTHORITY;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.CONTENT_URI_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_MOVIE;

public class MoviesProvider extends ContentProvider {

    private static final int MOVIE = 1;
    private static final int MOVIE_ID = 2;

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(AUTHORITY, TABLE_MOVIE, MOVIE);
        uriMatcher.addURI(AUTHORITY,
                TABLE_MOVIE + "/#",
                MOVIE_ID);
    }

    private ShowHelper showHelper;


    @Override
    public boolean onCreate() {
        showHelper = ShowHelper.getInstance(getContext());
        return true;
    }

    @Override
    public Cursor query(@NonNull Uri uri, @NonNull String[] strings, @NonNull String s, @NonNull String[] strings1, @NonNull String s1) {
        showHelper.open();
        Cursor cursor;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                cursor = showHelper.queryProviderMovie();
                break;
            case MOVIE_ID:
                cursor = showHelper.queryByIdProviderMovie(uri.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }

        return cursor;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Override
    public Uri insert(@NonNull Uri uri, @NonNull ContentValues contentValues) {
        showHelper.open();
        long added;
        switch (uriMatcher.match(uri)) {
            case MOVIE:
                added = showHelper.insertProviderMovie(contentValues);
                break;
            default:
                added = 0;
                break;
        }

//        if (getContext() != null){
//            getContext().getContentResolver().notifyChange(CONTENT_URI_MOVIE, new Favorite.DataObserver(new Handler(), getContext()));
//        }

        return Uri.parse(CONTENT_URI_MOVIE + "/" + added);
    }

    @Override
    public int delete(@NonNull Uri uri, @NonNull String s, @NonNull String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @NonNull ContentValues contentValues, @NonNull String s, @NonNull String[] strings) {
        return 0;
    }
}
