package com.ariemay.entertainmentlistmade3.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.MoviesAdapter;
import com.ariemay.entertainmentlistmade3.databases.ShowHelper;
import com.ariemay.entertainmentlistmade3.models.Movies;

import java.util.ArrayList;

public class MoviesFavorite extends Fragment {

    private RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private ArrayList<Movies> movieList;
    private ShowHelper showHelper;

    public MoviesFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_movies_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_movie_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new MoviesAdapter(getContext());
        showHelper = new ShowHelper(getContext());
        showHelper.open();
        movieList = new ArrayList<>();
        movieList = showHelper.getAllMovies();
        adapter.setMovies(movieList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void onStart(){
        super.onStart();
        ShowHelper item = new ShowHelper(getContext());
        item.open();
        movieList=item.getAllMovies();
        adapter.setMovies(movieList);
        recyclerView.setAdapter(adapter);
    }
}
