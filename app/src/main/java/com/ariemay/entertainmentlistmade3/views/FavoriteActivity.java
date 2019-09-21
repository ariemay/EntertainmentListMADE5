package com.ariemay.entertainmentlistmade3.views;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;

public class FavoriteActivity extends Fragment implements BottomNavigationView.OnNavigationItemSelectedListener {

    public FavoriteActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        loadFragment(new MoviesFavorite());
        BottomNavigationView bottomNavigationView = view.findViewById(R.id.favorite_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    private boolean loadFragment(Fragment fragment) {

        if (fragment != null) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.favorite_container, fragment)
                    .commit();
            return true;
        }   return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.movies_fav:
                fragment = new MoviesFavorite();
                break;
            case R.id.tv_fav:
                fragment = new TvFavorite();
                break;
        }   return loadFragment(fragment);
    }
}

