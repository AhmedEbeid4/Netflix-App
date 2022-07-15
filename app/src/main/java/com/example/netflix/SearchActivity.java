package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Random;

public class SearchActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchView searchField;
    private static ArrayList<Movie> movies;
    private MovieAdapter movieAdapter;
    private Integer i;
    ProgressBar progressBar;
    boolean isScrolling=false;
    GridLayoutManager manager;
    int currentItems,totalItems,scrollOutItems;
    ArrayList<Movie> searchedMovieArrayList;
    private ArrayList<Actors> NemoActors;
    private ArrayList<Actors> AvangersActors;
    private ArrayList<Actors> RatatouilleActors;
    private ArrayList<Actors> SpiderManActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        NemoActors= new ArrayList<>();
        NemoActors.add(new Actors("Nemo",R.drawable.nemo1));
        NemoActors.add(new Actors("Marlin",R.drawable.marlin));
        NemoActors.add(new Actors("Negil",R.drawable.negil));
        NemoActors.add(new Actors("Ray",R.drawable.ray));
        NemoActors.add(new Actors("Dory",R.drawable.dory));
        NemoActors.add(new Actors("Crush",R.drawable.crush));
        NemoActors.add(new Actors("Bruce",R.drawable.bruce));

        AvangersActors= new ArrayList<>();
        AvangersActors.add(new Actors("Captin America",R.drawable.captin_amarica));
        AvangersActors.add(new Actors("Iron Man",R.drawable.iron_man));
        AvangersActors.add(new Actors("Jasper",R.drawable.jasper_sitwell));

        RatatouilleActors= new ArrayList<>();
        RatatouilleActors.add(new Actors("Remy",R.drawable.remy));
        RatatouilleActors.add(new Actors("Emill",R.drawable.emill));
        RatatouilleActors.add(new Actors("Django",R.drawable.django));
        RatatouilleActors.add(new Actors("Gusteau",R.drawable.gusteau));
        RatatouilleActors.add(new Actors("Horst",R.drawable.horst));

        SpiderManActors= new ArrayList<>();
        SpiderManActors.add(new Actors("Spider Man",R.drawable.spider_man1));
        SpiderManActors.add(new Actors("Ben Barker",R.drawable.ben_barker));
        SpiderManActors.add(new Actors("George Stacy",R.drawable.george_stacy));




        searchedMovieArrayList=new ArrayList<>();
        movies=HomeActivity.getMovies();

        progressBar=(ProgressBar) findViewById(R.id.search_progress_bar);
        movieAdapter= new MovieAdapter(this);
        movieAdapter.setMovies(movies);
        recyclerView=(RecyclerView) findViewById(R.id.searchResult);
        recyclerView.setAdapter(movieAdapter);
        manager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        searchField=(SearchView)findViewById(R.id.searchField);
        BottomNavigationView bar =(BottomNavigationView) findViewById(R.id.bar);
        bar.setSelectedItemId(R.id.search);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            public boolean onNavigationItemSelected(MenuItem item) {
            if (item.getItemId() == R.id.home){
                goToHome();
            }else if (item.getItemId() == R.id.favourite){
                goToFavouriteActivity();
            }else if(item.getItemId() == R.id.search){
                Toast.makeText(SearchActivity.this,"You are already in search page!",Toast.LENGTH_SHORT).show();
            }else {
                goToDownloadsActivity();
            }
            return false;
        }
    });
        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged( RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;

                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItems=manager.getChildCount();
                totalItems=manager.getItemCount();
                scrollOutItems=manager.findFirstVisibleItemPosition();
                if (isScrolling && (currentItems+scrollOutItems) == totalItems){
                    isScrolling=false;
                    progressBar.setVisibility(View.VISIBLE);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            uploadMore();
                            if (searchedMovieArrayList.size()!=0){
                                movieAdapter.setMovies(searchedMovieArrayList);
                            }else {
                                movieAdapter.setMovies(movies);
                            }
                            movieAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                    },2500);

                }
            }
        });
    searchField.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            searchedMovieArrayList= new ArrayList<>();
            for (int i =0 ; i<movies.size();i++){
                if (movies.get(i).getTitle().toLowerCase().contains(newText.toLowerCase())){
                    searchedMovieArrayList.add(movies.get(i));
                }
            }
            if (newText.length()==0){
                searchedMovieArrayList=movies;
            }
            movieAdapter.setMovies(searchedMovieArrayList);
            recyclerView.setAdapter(movieAdapter);
            return false;
        }
    });
    movieAdapter.setOnClick(new MovieAdapter.onClickListener() {
        @Override
        public void onClick(int position) {
            Intent i =new Intent(SearchActivity.this,show_details_activity.class);
            i.putExtra("index",position);
            i.putExtra("sender","search");
            startActivity(i);
        }
    });

    }
    private void goToHome(){
        startActivity(new Intent(SearchActivity.this,HomeActivity.class));
        overridePendingTransition(0,0);
    }
    private void uploadMore(){
        Movie[] movies1 = {
                new Movie("SpiderMan", 9.8, R.drawable.spider_man, 1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", SpiderManActors,"android.resource://"+getPackageName()+"/"+R.raw.v3),
                new Movie("Avangers", 4.5, R.drawable.avangers_image, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", AvangersActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Nemo", 10.0, R.drawable.nemo, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", NemoActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Ratatouille", 10.0, R.drawable.ratatouille, 4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", RatatouilleActors,"android.resource://"+getPackageName()+"/"+R.raw.v1)
        };
        for (int i =0 ; i<12;i++){
            movies.add(movies1[new Random().nextInt(4)]);
            if (isIn(movies.get(movies.size()-1))){
                searchedMovieArrayList.add(movies.get(movies.size()-1));
            }
        }
    }
    private Boolean isIn(Movie m){
        boolean g=false;
        if (searchedMovieArrayList.size()>0){
            for(int i =0; i<searchedMovieArrayList.size();i++){
                if (searchedMovieArrayList.get(i).getTitle().equals(m.getTitle()) && searchedMovieArrayList.get(i).getId()==(m.getId())){
                    g=true;
                }
            }
        }
        return g;
    }
    private void goToFavouriteActivity() {
        Intent i = new Intent(this, FavouriteActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    private void goToDownloadsActivity() {
        Intent i = new Intent(this, DownloadsActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    public static ArrayList<Movie> getMovies() {
        return movies;
    }
}