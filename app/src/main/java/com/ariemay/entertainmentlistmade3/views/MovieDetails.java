package com.ariemay.entertainmentlistmade3.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.databases.DBContract;
import com.ariemay.entertainmentlistmade3.databases.DBHelper;
import com.ariemay.entertainmentlistmade3.databases.ShowHelper;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_MOVIE;

public class MovieDetails extends AppCompatActivity {

    public static String EXTRA_DATA = "extras";

    TextView name, genre, date, actor, description;
    ImageView poster;
    ToggleButton favoriteButton;

    private ShowHelper showHelper;
    private Movies movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movies = getIntent().getParcelableExtra(EXTRA_DATA);

        poster = findViewById(R.id.detail_movie_poster);
        name = findViewById(R.id.detail_movie_name);
        date = findViewById(R.id.detail_date);
        description = findViewById(R.id.description);
        favoriteButton = findViewById(R.id.button_favorite);

        Glide.with(getApplicationContext())
                .load(movies.getBackdrop_path())
                .apply(new RequestOptions()
                        .override(150,150))
                .into(poster);

        name.setText(movies.getTitle());
        date.setText(movies.getRelease_date());
        description.setText(movies.getOverview());

        if (Exist(movies.getTitle())) {
            favoriteButton.setChecked(true);
            Log.d("EXIST", "movies exist");
            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!isChecked) {
                        int movie_id = movies.getId();
                        showHelper = new ShowHelper(MovieDetails.this);
                        showHelper.open();
                        showHelper.deleteMovie(movie_id);
                        showHelper.close();
                        Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_LONG).show();
                        favoriteButton.setChecked(false);
                    }
                }
            });
        } else {
            favoriteButton.setChecked(false);
            Log.d("NO EXIST", "no movies exist");
            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        Log.d("CHECKED 2", "checked");
                        saveFavorite();
                        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
                        favoriteButton.setChecked(true);
                    }
                }
            });

        }
    }

    private void saveFavorite() {
        Log.d("POSTER", "saveFavorite: " + movies.getPoster_path());
        showHelper = new ShowHelper(this);
        String poster = movies.getPoster_path();
        String detailPoster = movies.getBackdrop_path();
        String movieName = movies.getTitle();
        int movieVote = movies.getVote_average();
        String overview = movies.getOverview();
        String releaseDate = movies.getRelease_date();

        movies.setTitle(movieName);
        movies.setOverview(overview);
        movies.setPoster_path(poster);
        movies.setBackdrop_path(detailPoster);
        movies.setRelease_date(releaseDate);
        movies.setVote_average(movieVote);

        showHelper.open();
        showHelper.insertMovie(movies);
        showHelper.close();
    }

    public boolean Exist(String item){
        String selected = DBContract.MovieColumns.TITLE_MOVIE+" =?";
        String[] selectedArgs={item};
        String limit="1";
        showHelper= new ShowHelper(this);
        showHelper.open();
        DBHelper dataBaseHelper= new DBHelper(MovieDetails.this);
        SQLiteDatabase database = dataBaseHelper.getWritableDatabase();
        Cursor cursor= database.query(TABLE_MOVIE,null,selected,selectedArgs,null,null,null,limit);
        boolean exists;
        exists=(cursor.getCount() > 0);
        cursor.close();
        showHelper.close();
        return exists;
    }
}
