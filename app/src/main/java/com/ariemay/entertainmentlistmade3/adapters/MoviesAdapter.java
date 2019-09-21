package com.ariemay.entertainmentlistmade3.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.views.MovieDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {
    public Context context;
    private ArrayList<Movies> movies = new ArrayList<>();

    public MoviesAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(ArrayList<Movies> items) {
        movies.clear();
        movies.addAll(items);
        Log.i("SETMOVIES", movies.toString());
        notifyDataSetChanged();
    }

    public void addMovies(final Movies item) {
        movies.add(item);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        movies.clear();
    }

    @NonNull
    @Override
    public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_list_items, viewGroup, false);
        return new MoviesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesViewHolder viewHolder, final int i) {
        Glide.with(context)
                .load(movies.get(i).getPoster_path())
                .apply(new RequestOptions().override(150, 150))
                .into(viewHolder.poster);

        viewHolder.date.setText(movies.get(i).getRelease_date());
        viewHolder.name.setText(movies.get(i).getTitle());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView date;
        private ImageView poster;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            date = itemView.findViewById(R.id.date);
            poster = itemView.findViewById(R.id.movie_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, MovieDetails.class);
                    intent.putExtra(MovieDetails.EXTRA_DATA, movies.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }
}
