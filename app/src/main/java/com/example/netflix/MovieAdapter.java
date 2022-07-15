package com.example.netflix;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {
    private ArrayList<Movie> movies;
    private Context context;
    onClickListener onClick;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_layout,parent,false);
        return new ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {
        Movie a=movies.get(position);
        holder.title.setText(a.getTitle());
        int imageResource=a.getPosterPath();
        //Picasso.with(context).load(imageUrl).into(holder.poster);
        holder.poster.setImageResource(imageResource);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setOnClick(onClickListener onClick) {
        this.onClick = onClick;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView poster;
        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            onClick.onClick(position);
                        }
                    }
                }
            });
            title= itemView.findViewById(R.id.movie_title);

            poster=itemView.findViewById(R.id.movie_poster);
        }
    }
    interface onClickListener{
        void onClick(int position);
    }
}


