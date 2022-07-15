package com.example.netflix;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class FavouriteMoviesAdapter extends RecyclerView.Adapter<FavouriteMoviesAdapter.viewHolder> {
    private Context context;
    private ArrayList<Movie> likedMovies;
    private onClickListener onClick;
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_movie_layout,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteMoviesAdapter.viewHolder holder, int position) {
        Movie favouriteMovie=likedMovies.get(position);
        holder.movieRate.setText(""+favouriteMovie.getRate());
        if (favouriteMovie.getRate()==10){
            holder.movieRate.setTextColor(Color.parseColor("#0081A4"));
        }else if (favouriteMovie.getRate()>5){
            holder.movieRate.setTextColor(Color.parseColor("#9a9a9a"));
        }else {
            holder.movieRate.setTextColor(Color.parseColor("#FF2200"));
        }
        holder.moviePoster.setImageResource(favouriteMovie.getPosterPath());
        holder.movieTitle.setText(favouriteMovie.getTitle());
    }

    public void setOnClick(onClickListener onClick) {
        this.onClick = onClick;
    }

    @Override
    public int getItemCount() {
        return likedMovies.size();
    }

    public void setLikedMovies(ArrayList<Movie> likedMovies) {
        this.likedMovies = likedMovies;
    }

    class viewHolder extends RecyclerView.ViewHolder{
        ImageView moviePoster;
        TextView movieTitle;
        TextView movieRate;
        TextView likedText;
        ImageView heatBtn;
        LinearLayout btnFather;
        public viewHolder( View itemView) {
            super(itemView);
            moviePoster=(ImageView) itemView.findViewById(R.id.movie_image);
            movieTitle=(TextView) itemView.findViewById(R.id.movie_name);
            movieRate=(TextView) itemView.findViewById(R.id.movie_rate);
            likedText=(TextView) itemView.findViewById(R.id.likedText);
            heatBtn=(ImageView) itemView.findViewById(R.id.heat_btn);
            btnFather=(LinearLayout) itemView.findViewById(R.id.likeBtnFather);

            btnFather.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick!=null){
                        int position = getAdapterPosition();
                        if (position !=RecyclerView.NO_POSITION){
                            onClick.onUnlike(position);
                        }
                    }
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClick!=null){
                        int position = getAdapterPosition();
                        if (position !=RecyclerView.NO_POSITION){
                            onClick.onClick(position);
                        }
                    }
                }
            });
        }
    }
    interface onClickListener{
        void onClick(int position);
        void onUnlike(int position);
    }
}
