package com.ariemay.entertainmentlistmade3.databases;

import android.net.Uri;
import android.provider.BaseColumns;

public class DBContract {
    public static String AUTHORITY = "com.ariemay.entertainmentlistmade3";
    private static final String SCHEME_MOVIE = "content_movie";
    private static final String SCHEME_TV = "content_tv";
    public static String TABLE_MOVIE = "movie";
    public static String TABLE_TV = "tv";
    public static final class MovieColumns implements BaseColumns {
        public static String ID_MOVIE = "id_movie";
        public static String TITLE_MOVIE = "title";
        public static String VOTE_AVERAGE_MOVIE = "vote_average";
        public static String OVERVIEW_MOVIE = "overview";
        public static String RELEASE_DATE_MOVIE = "release_date";
        public static String POSTER_PATH_MOVIE = "poster_path";
        public static String BACKDROP_PATH_MOVIE = "backdrop_path";
    }
    public static final class TvColumns implements BaseColumns{
        public static String ID_TV = "id_tv";
        public static String NAME_TV = "name";
        public static String FIRST_AIR_DATE_TV = "first_air_date";
        public static String VOTE_AVERAGE_TV = "vote_average";
        public static String OVERVIEW_TV = "overview";
        public static String POSTER_PATH_TV = "poster_path";
        public static String BACKDROP_PATH_TV = "backdrop_path";
    }

    public static final Uri CONTENT_URI_MOVIE = new Uri.Builder().scheme(SCHEME_MOVIE)
            .authority(AUTHORITY)
            .appendPath(TABLE_MOVIE)
            .build();

    public static final Uri CONTENT_URI_TV = new Uri.Builder().scheme(SCHEME_TV)
            .authority(AUTHORITY)
            .appendPath(TABLE_TV)
            .build();
}
