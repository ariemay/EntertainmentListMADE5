package com.ariemay.entertainmentlistmade3.fragments;


import android.app.ProgressDialog;
import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.MoviesAdapter;
import com.ariemay.entertainmentlistmade3.adapters.TVAdapter;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.ariemay.entertainmentlistmade3.services.viewmodels.MoviesViewModel;
import com.ariemay.entertainmentlistmade3.services.viewmodels.TVViewModel;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVListFragment extends Fragment {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private ArrayList<TV> listTV = new ArrayList<>();
    private TVAdapter adapter;
    private Context context;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public TVListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_tvlist, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.loading));

        if (listTV.size() <= 0) {
            progressDialog.show();
        } else {
            progressDialog.dismiss();
        }

        TVViewModel tvViewModel = ViewModelProviders.of(this).get(TVViewModel.class);
        tvViewModel.getTVShow().observe(this, getTv);
        tvViewModel.setTV();

        RecyclerView rvFragmentTV = view.findViewById(R.id.tv_list);
        rvFragmentTV.setHasFixedSize(true);
        rvFragmentTV.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new TVAdapter(view.getContext());
        adapter.setTV(listTV);
        rvFragmentTV.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        final TVViewModel tvViewModel = ViewModelProviders.of(this).get(TVViewModel.class);

        SearchManager searchManager = (SearchManager) Objects.requireNonNull(getActivity()).getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);
                    if (newText.length() == 0) {
                        tvViewModel.setTV();
                    } else {
                        tvViewModel.searchTV(newText);
                    }
                    return true;
                }
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }

    private Observer<ArrayList<TV>> getTv = new Observer<ArrayList<TV>>() {
        @Override
        public void onChanged(ArrayList<TV> tvItems) {
            if (tvItems != null) {
                listTV = tvItems;
                adapter.setTV(tvItems);
            }
            progressDialog.dismiss();
        }
    };
}
