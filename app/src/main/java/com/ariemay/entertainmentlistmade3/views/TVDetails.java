package com.ariemay.entertainmentlistmade3.views;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.databases.DBContract;
import com.ariemay.entertainmentlistmade3.databases.DBHelper;
import com.ariemay.entertainmentlistmade3.databases.ShowHelper;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_MOVIE;
import static com.ariemay.entertainmentlistmade3.databases.DBContract.TABLE_TV;

public class TVDetails extends AppCompatActivity {

    public static String EXTRA_DATA = "extras";

    TextView name, startDate, endDate, rating, description;
    ImageView poster;
    ToggleButton favoriteButton;

    private TV tv;
    private ShowHelper showHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvdetails);

        tv = getIntent().getParcelableExtra(EXTRA_DATA);

        name = findViewById(R.id.detail_tv_name);
        startDate = findViewById(R.id.start);
        endDate = findViewById(R.id.end);
        rating = findViewById(R.id.rating);
        description = findViewById(R.id.description);
        poster = findViewById(R.id.detail_tv_poster);
        favoriteButton = findViewById(R.id.button_favorite);

        Glide.with(getApplicationContext())
                .load(tv.getBackdrop_path())
                .apply(new RequestOptions()
                        .override(150,150))
                .into(poster);

        name.setText(tv.getName());
        startDate.setText(String.valueOf(tv.getFirst_air_date()));
        rating.setText(tv.getVote_average());
        description.setText(tv.getOverview());

        if (Exist(tv.getName())) {
            favoriteButton.setChecked(true);
            Log.d("EXIST", "tv exist");
            favoriteButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (!isChecked) {
                        int tv_id = tv.getId();
                        showHelper = new ShowHelper(TVDetails.this);
                        showHelper.open();
                        showHelper.deleteTv(tv_id);
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
        Log.d("POSTER", "saveFavorite: " + tv.getPoster_path());
        showHelper = new ShowHelper(this);
        String poster = tv.getPoster_path();
        String detailPoster = tv.getBackdrop_path();
        String tvName = tv.getName();
        String tvVote_average = tv.getVote_average();
        String overview = tv.getOverview();
        String first_air_date = tv.getFirst_air_date();

        tv.setName(tvName);
        tv.setOverview(overview);
        tv.setPoster_path(poster);
        tv.setBackdrop_path(detailPoster);
        tv.setFirst_air_date(first_air_date);
        tv.setVote_average(tvVote_average);

        showHelper.open();
        showHelper.insertTv(tv);
        showHelper.close();
    }

    public boolean Exist(String item){
        String selected = DBContract.TvColumns.NAME_TV+" =?";
        String[] selectedArgs={item};
        String limit="1";
        Log.d("ARGS", selectedArgs.toString());
        showHelper= new ShowHelper(this);
        showHelper.open();
        DBHelper dataBaseHelper= new DBHelper(TVDetails.this);
        database = dataBaseHelper.getWritableDatabase();
        Cursor cursor= database.query(TABLE_TV,null,selected,selectedArgs,null,null,null,limit);
        boolean exists;
        exists=(cursor.getCount() > 0);
        cursor.close();
        showHelper.close();
        return exists;
    }
}
