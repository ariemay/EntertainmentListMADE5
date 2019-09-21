package com.ariemay.entertainmentlistmade3.views;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.TVAdapter;
import com.ariemay.entertainmentlistmade3.databases.ShowHelper;
import com.ariemay.entertainmentlistmade3.models.TV;

import java.util.ArrayList;

public class TvFavorite extends Fragment {

    private RecyclerView recyclerView;
    private TVAdapter adapter;
    private ArrayList<TV> tvs;
    private ShowHelper showHelper;

    public TvFavorite() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_tv_favorite, container, false);
        recyclerView = view.findViewById(R.id.rv_tv_favorite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TVAdapter(getContext());
        showHelper = new ShowHelper(getContext());
        showHelper.open();
        tvs = new ArrayList<>();
        tvs = showHelper.getAllTv();
        adapter.setTV(tvs);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        ShowHelper item = new ShowHelper(getContext());
        item.open();
        tvs = item.getAllTv();
        adapter.setTV(tvs);
        recyclerView.setAdapter(adapter);
    }
}
