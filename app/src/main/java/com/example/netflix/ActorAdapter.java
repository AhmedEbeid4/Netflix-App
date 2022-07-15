package com.example.netflix;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder>{
    private ArrayList<Actors> actors;
    private Context context;
    public ActorAdapter(Context context) {
        this.context=context;
    }

    public void setActors(ArrayList<Actors> actors) {
        this.actors = actors;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item_layout,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ActorAdapter.ViewHolder holder, int position) {
        Actors a = actors.get(position);
        holder.actorImage.setImageResource(a.getImageResource());
        holder.actorName.setText(a.getName());
    }

    @Override
    public int getItemCount() {
        return actors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView actorImage;
        TextView actorName;
        public ViewHolder(View itemView) {
            super(itemView);
            actorName=(TextView) itemView.findViewById(R.id.actor_name);
            actorImage=(ImageView) itemView.findViewById(R.id.actorImage);
        }
    }
}
