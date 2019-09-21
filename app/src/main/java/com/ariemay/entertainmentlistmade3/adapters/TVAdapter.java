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
import com.ariemay.entertainmentlistmade3.models.TV;
import com.ariemay.entertainmentlistmade3.views.TVDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class TVAdapter extends RecyclerView.Adapter<TVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<TV> tvs = new ArrayList<>();

    public TVAdapter(Context context) {
        this.context = context;
    }

    public void setTV(ArrayList<TV> items) {
        tvs.clear();
        tvs.addAll(items);
        Log.i("TVS", tvs.toString());
        notifyDataSetChanged();
    }

    public void addTV(final TV item) {
        tvs.add(item);
        notifyDataSetChanged();
    }

    public void clearTV() {
        tvs.clear();
    }

    @NonNull
    @Override
    public TVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.tv_list_items, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TVAdapter.ViewHolder viewHolder, int i) {
        Glide.with(context)
                .load(tvs.get(i).getPoster_path())
                .apply(new RequestOptions().override(150,150))
                .into(viewHolder.tvPhoto);

        viewHolder.tvName.setText(tvs.get(i).getName());
        viewHolder.tvRating.setText(tvs.get(i).getVote_average());

    }

    @Override
    public int getItemCount() {
        return tvs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvRating;
        private ImageView tvPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.showName);
            tvRating = itemView.findViewById(R.id.rating);
            tvPhoto = itemView.findViewById(R.id.tv_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(context, TVDetails.class);
                    intent.putExtra(TVDetails.EXTRA_DATA, tvs.get(position));
                    context.startActivity(intent);
                }
            });
        }
    }
}
