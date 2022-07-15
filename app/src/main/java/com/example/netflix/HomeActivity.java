package com.example.netflix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private static ArrayList<Movie> movies;
    private MovieAdapter movieAdapter1;
    private MovieAdapter movieAdapter2;
    private Integer i;
    private int current_items1, current_items2, total_items1, total_items2, out_items1, out_items2;
    private boolean isScrolling1, isScrolling2;
    private LinearLayoutManager manager2;
    private LinearLayoutManager manager1;
    private ProgressBar progressBar1, progressBar2;
    private static int id = 1;
    private ArrayList<Actors> NemoActors;
    private ArrayList<Actors> AvangersActors;
    private ArrayList<Actors> RatatouilleActors;
    private ArrayList<Actors> SpiderManActors;
    private static ArrayList<Movie> arrayList1;
    private static ArrayList<Movie> arrayList2;
    private LinearLayout likeBtn;
    private ImageView likeImage;
    private TextView likeText;
    private static int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        NemoActors = new ArrayList<>();
        NemoActors.add(new Actors("Nemo", R.drawable.nemo1));
        NemoActors.add(new Actors("Marlin", R.drawable.marlin));
        NemoActors.add(new Actors("Negil", R.drawable.negil));
        NemoActors.add(new Actors("Ray", R.drawable.ray));
        NemoActors.add(new Actors("Dory", R.drawable.dory));
        NemoActors.add(new Actors("Crush", R.drawable.crush));
        NemoActors.add(new Actors("Bruce", R.drawable.bruce));

        AvangersActors = new ArrayList<>();
        AvangersActors.add(new Actors("Captin America", R.drawable.captin_amarica));
        AvangersActors.add(new Actors("Iron Man", R.drawable.iron_man));
        AvangersActors.add(new Actors("Jasper", R.drawable.jasper_sitwell));

        RatatouilleActors = new ArrayList<>();
        RatatouilleActors.add(new Actors("Remy", R.drawable.remy));
        RatatouilleActors.add(new Actors("Emill", R.drawable.emill));
        RatatouilleActors.add(new Actors("Django", R.drawable.django));
        RatatouilleActors.add(new Actors("Gusteau", R.drawable.gusteau));
        RatatouilleActors.add(new Actors("Horst", R.drawable.horst));

        SpiderManActors = new ArrayList<>();
        SpiderManActors.add(new Actors("Spider Man", R.drawable.spider_man1));
        SpiderManActors.add(new Actors("Ben Barker", R.drawable.ben_barker));
        SpiderManActors.add(new Actors("George Stacy", R.drawable.george_stacy));

        progressBar1 = (ProgressBar) findViewById(R.id.f_progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.s_progress_bar);
        Movie[] movies1 = {
                new Movie("SpiderMan", 9.8, R.drawable.spider_man, 1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", SpiderManActors,"android.resource://"+getPackageName()+"/"+R.raw.v3),
                new Movie("Avangers", 4.5, R.drawable.avangers_image, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", AvangersActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Nemo", 10.0, R.drawable.nemo, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", NemoActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Ratatouille", 10.0, R.drawable.ratatouille, 4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", RatatouilleActors,"android.resource://"+getPackageName()+"/"+R.raw.v1)
        };
        movies = new ArrayList<>();
        i = 1;
        Movie we=getRandom();
        we.setId(id++);
        movies.add(we);
        for (int i = 0; i < 12; i++) {
            Movie we1=movies1[this.i++];
            we1.setId(id++);
            movies.add(we1);
            if (this.i > 3) {
                this.i = 0;
            }
        }
        likeBtn=(LinearLayout) findViewById(R.id.like_button);
        likeImage=(ImageView) findViewById(R.id.like_image);
        likeText=(TextView) findViewById(R.id.like_text);
        arrayList1 = subArray(movies, 0, movies.size() / 2);
        arrayList2 = subArray(movies, movies.size() / 2, movies.size() - 1);
        movieAdapter1 = new MovieAdapter(this);
        movieAdapter2 = new MovieAdapter(this);
        movieAdapter1.setMovies(arrayList1);
        recyclerView1 = (RecyclerView) findViewById(R.id.first_rec);
        recyclerView1.setAdapter(movieAdapter1);
        manager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(manager1);
        recyclerView2 = (RecyclerView) findViewById(R.id.second_rec);
        movieAdapter2.setMovies(arrayList2);
        recyclerView2.setAdapter(movieAdapter2);
        manager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(manager2);
        ImageView g = (ImageView) findViewById(R.id.header_image);

        Random r = new Random();
        number = r.nextInt(movies.size());
        g.setImageResource(movies.get(number).getPosterPath());
        TextView h = (TextView) findViewById(R.id.header_title);
        h.setText(movies.get(number).getTitle());
        LinearLayout infoBtn = (LinearLayout) findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, show_details_activity.class);
                i.putExtra("index", number);
                i.putExtra("1or2",0);
                i.putExtra("sender", "home");
                startActivity(i);
            }
        });
        if (isIn(movies.get(number))){
            likeImage.setImageResource(R.drawable.ic_baseline_favorite_24);
            likeText.setText("Liked");
        }
        BottomNavigationView bar = (BottomNavigationView) findViewById(R.id.bar);
        
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Toast.makeText(HomeActivity.this, "You are already in home!", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.favourite) {
                    goToFavouriteActivity();
                } else if (item.getItemId() == R.id.search) {
                    goToSearchActivity();
                } else {
                    goToDownloadsActivity();
                }
                return false;
            }
        });
        recyclerView1.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling1 = true;
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                total_items1 = manager1.getItemCount();
                current_items1 = manager1.getChildCount();
                out_items1 = manager1.findFirstVisibleItemPosition();
                if (isScrolling1 && (current_items1 + out_items1) == total_items1) {
                    progressBar1.setVisibility(View.VISIBLE);
                    isScrolling1 = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 8; i++) {
                                arrayList1.add(movies1[new Random().nextInt(4)]);
                                arrayList1.get(arrayList2.size() - 1).setId(id++);
                            }
                            movieAdapter1.notifyDataSetChanged();
                            progressBar1.setVisibility(View.GONE);
                        }
                    }, 2500);

                }
            }
        });
        recyclerView2.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling2 = true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                current_items2 = manager2.getChildCount();
                total_items2 = manager2.getItemCount();
                out_items2 = manager2.findFirstVisibleItemPosition();
                if (isScrolling2 && (current_items2 + out_items2) == total_items2) {
                    progressBar2.setVisibility(View.VISIBLE);
                    isScrolling2 = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < 8; i++) {
                                arrayList2.add(movies1[new Random().nextInt(4)]);
                                arrayList2.get(arrayList2.size() - 1).setId(id++);
                            }
                            movieAdapter2.notifyDataSetChanged();
                            progressBar2.setVisibility(View.GONE);
                        }
                    }, 2500);
                }
            }
        });
        movieAdapter1.setOnClick(new MovieAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                Intent i = new Intent(HomeActivity.this, show_details_activity.class);
                i.putExtra("index", position);
                i.putExtra("1or2",1);
                i.putExtra("sender", "home");
                startActivity(i);
            }
        });
        movieAdapter2.setOnClick(new MovieAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                Intent i = new Intent(HomeActivity.this, show_details_activity.class);
                i.putExtra("index", position);
                i.putExtra("1or2",2);
                i.putExtra("sender", "home");
                startActivity(i);

            }
        });
        actions();
    }

    private Movie getRandom() {
        Movie[] movies1 = {
                new Movie("SpiderMan", 9.8, R.drawable.spider_man, 1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", SpiderManActors,"android.resource://"+getPackageName()+"/"+R.raw.v3),
                new Movie("Avangers", 4.5, R.drawable.avangers_image, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", AvangersActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Nemo", 10.0, R.drawable.nemo, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", NemoActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Ratatouille", 10.0, R.drawable.ratatouille, 4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", RatatouilleActors,"android.resource://"+getPackageName()+"/"+R.raw.v1)
        };
        Random r = new Random();
        return movies1[r.nextInt(movies1.length)];
    }

    private ArrayList<Movie> subArray(ArrayList<Movie> movies, int start, int end) {
        ArrayList<Movie> arrayList = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            arrayList.add(movies.get(i));
        }
        return arrayList;
    }

    private void goToSearchActivity() {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
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

    public static ArrayList<Movie> getArrayList1() {
        return arrayList1;
    }

    public static ArrayList<Movie> getArrayList2() {
        return arrayList2;
    }

    public static ArrayList<Movie> getMovies() {
        return movies;
    }

    private void actions(){
        likingButton();
        playAction();
    }
    private void likingButton(){
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesTable db= new MoviesTable(HomeActivity.this);
                Movie mainMovie=movies.get(number);
                if (likeText.getText().equals("Like")){
                    ArrayList<Movie> movieArrayList=db.getData();
                    if (movieArrayList.size() ==0){
                        mainMovie.setId(1);
                    }else {
                        mainMovie.setId(movieArrayList.get(movieArrayList.size()-1).getId()+1);
                    }
                    String h=db.insert(mainMovie);
                    Toast.makeText(HomeActivity.this,h,Toast.LENGTH_SHORT).show();
                    likeImage.setImageResource(R.drawable.ic_baseline_favorite_24);
                    likeText.setText("Liked");
                }else {
                    likeText.setText("Like");
                    likeImage.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    db.delete(mainMovie.getId());
                    Toast.makeText(HomeActivity.this,"Removed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean isIn(Movie m){
        boolean r = false;
        MoviesTable db= new MoviesTable(HomeActivity.this);
        ArrayList<Movie> movieArrayList=db.getData();
        for (int i =0 ; i<movieArrayList.size();i++){
            if (m.getId() == movieArrayList.get(i).getId() && m.getTitle().equals(movieArrayList.get(i).getTitle())){
                r=true;
                break;
            }
        }
        return r;
    }

    private void playAction(){
        LinearLayout btn=(LinearLayout) findViewById(R.id.playBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,VideoActivity.class));
                overridePendingTransition(0,0);
            }
        });
    }
    static Movie getMovie(){
        return movies.get(number);
    }
    @Override
    public void onBackPressed() {

    }
}