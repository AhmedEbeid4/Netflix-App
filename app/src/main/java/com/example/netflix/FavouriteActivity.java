package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private FavouriteMoviesAdapter adapter;
    private ArrayList<Movie> likedMovies;
    private MoviesTable db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
        BottomNavigationView bar = (BottomNavigationView) findViewById(R.id.bar);
        db=new MoviesTable(this);
        likedMovies= db.getData();
        getSupportActionBar().hide();
        manager= new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        bar.setSelectedItemId(R.id.favourite);

        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    goToHome();
                } else if (item.getItemId() == R.id.favourite) {
                    Toast.makeText(FavouriteActivity.this,"You are in favourite",Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.search) {
                    goToSearchActivity();
                } else {
                    goToDownloadsActivity();
                }
                return false;
            }
        });
        recyclerView=(RecyclerView) findViewById(R.id.favourite_recyclerView);
        adapter=new FavouriteMoviesAdapter();
        adapter.setLikedMovies(likedMovies);
        adapter.setOnClick(new FavouriteMoviesAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                goToShowDetails(position);
            }

            @Override
            public void onUnlike(int position) {
                db.delete(likedMovies.get(position).getId());
                likedMovies.remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }
    private void goToSearchActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    private void goToHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    private void goToDownloadsActivity() {
        Intent i = new Intent(this, DownloadsActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    private void goToShowDetails(int position){
        Intent i = new Intent(this, show_details_activity.class);
        i.putExtra("index", position);
        i.putExtra("1or2",3);
        i.putExtra("sender", "favourite");
        startActivity(i);
    }
}