package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class show_details_activity extends AppCompatActivity {

    private ImageView posterImage;
    private TextView movieTitle;
    private TextView movieOverview;
    private TextView rate;
    private RelativeLayout button;
    private ActorAdapter actorAdapter;
    private RecyclerView actorsRecyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView movieRecyclerView;
    private static Movie mainMovie;
    private ArrayList<Movie> AnotherMovies;
    private LinearLayoutManager actorManager;
    private LinearLayoutManager movieManager;
    private int current_items,total_items,out_items;
    private ProgressBar progressBar;
    private boolean isScrolling=false;
    private ArrayList<Actors> NemoActors;
    private ArrayList<Movie> list;
    private ArrayList<Actors> AvangersActors;
    private ArrayList<Actors> RatatouilleActors;
    private ArrayList<Actors> SpiderManActors;
    private String sender;
    private int or=-1;
    private Integer position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details);
        getSupportActionBar().hide();
        Intent i = this.getIntent();

        if (i!=null){
            if (i.getStringExtra("dd") == null){
                if (i.getStringExtra("sender").equals("home")){
                    sender="home";
                    if(i.getIntExtra("1or2",-1)==0){
                        mainMovie= HomeActivity.getMovies().get(i.getIntExtra("index",0));
                        or=0;
                    }
                    else if (i.getIntExtra("1or2",-1)==1){
                        mainMovie= HomeActivity.getArrayList1().get(i.getIntExtra("index",0));
                        or=1;
                    }else if (i.getIntExtra("1or2",-1)==2){
                        or=2;
                        mainMovie= HomeActivity.getArrayList2().get(i.getIntExtra("index",0));
                    }
                }else if (i.getStringExtra("sender").equals("search")){
                    sender="search";
                    mainMovie= SearchActivity.getMovies().get(i.getIntExtra("index",0));
                }else if(i.getStringExtra("sender").equals("favourite")){
                    sender="favourite";
                    mainMovie=new MoviesTable(this).getData().get(i.getIntExtra("index",0));
                }
            }else {
                mainMovie=VideoActivity.getMovie();
            }
        }
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

        if (mainMovie.getActors() == null){
            if (mainMovie.getTitle().equals("SpiderMan")){
                mainMovie.setActors(SpiderManActors);
            }else if (mainMovie.getTitle().equals("Avangers")){
                mainMovie.setActors(AvangersActors);
            }
            else if (mainMovie.getTitle().equals("Nemo")){
                mainMovie.setActors(NemoActors);
            }else if (mainMovie.getTitle().equals("Ratatouille")){
                mainMovie.setActors(RatatouilleActors);
            }
        }
        progressBar=(ProgressBar) findViewById(R.id.SHOW_PROGRESS);
        posterImage=(ImageView) findViewById(R.id.show_image);
        actorManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        movieManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        movieTitle=(TextView) findViewById(R.id.show_movie_name);
        movieOverview=(TextView) findViewById(R.id.overview);
        rate=(TextView) findViewById(R.id.rate);
        button=(RelativeLayout) findViewById(R.id.watch_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(show_details_activity.this,VideoActivity.class);
                i.putExtra("index", position);
                if (or != -1){
                    i.putExtra("1or2",or);
                }
                i.putExtra("s","sh");
                i.putExtra("sender", sender);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        actorAdapter=new ActorAdapter(this);
        actorsRecyclerView=(RecyclerView) findViewById(R.id.actors_recyclerView);
        movieAdapter=new MovieAdapter(this);
        movieRecyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        AnotherMovies=HomeActivity.getMovies();
        list=new ArrayList<>();
        list.add(mainMovie);
        setUp(mainMovie);
        actions();
    }
    private void setUp(Movie mainMovie){
        Movie[] movies1 = {
                new Movie("SpiderMan", 9.8, R.drawable.spider_man, 1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", SpiderManActors,"android.resource://"+getPackageName()+"/"+R.raw.v3),
                new Movie("Avangers", 4.5, R.drawable.avangers_image, 2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", AvangersActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Nemo", 10.0, R.drawable.nemo, 3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", NemoActors,"android.resource://"+getPackageName()+"/"+R.raw.v2),
                new Movie("Ratatouille", 10.0, R.drawable.ratatouille, 4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", RatatouilleActors,"android.resource://"+getPackageName()+"/"+R.raw.v1)
        };
        isInLiked(mainMovie);
        posterImage.setImageResource(mainMovie.getPosterPath());
        movieTitle.setText(mainMovie.getTitle());
        movieOverview.setText(mainMovie.getDesc());
        rate.setText(mainMovie.getRate()+"");
        if (mainMovie.getRate()==10){
            rate.setTextColor(Color.parseColor("#0081A4"));
        }else if (mainMovie.getRate()>5){
            rate.setTextColor(Color.parseColor("#9a9a9a"));
        }else {
            rate.setTextColor(Color.parseColor("#FF2200"));
        }
        actorAdapter.setActors(mainMovie.getActors());
        actorsRecyclerView.setAdapter(actorAdapter);
        actorsRecyclerView.setLayoutManager(actorManager);

        movieAdapter.setMovies(AnotherMovies);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(movieManager);
        movieAdapter.setOnClick(new MovieAdapter.onClickListener() {
            @Override
            public void onClick(int position) {
                if (!AnotherMovies.get(position).getTitle().equals(show_details_activity.mainMovie.getTitle())){
                    setUp(AnotherMovies.get(position));
                    list.add(AnotherMovies.get(position));
                    show_details_activity.mainMovie=AnotherMovies.get(position);
                }
            }
        });
        movieRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling=true;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                current_items=movieManager.getChildCount();
                total_items=movieManager.getItemCount();
                out_items=movieManager.findFirstVisibleItemPosition();
                if (isScrolling && (current_items+out_items) == total_items){
                    progressBar.setVisibility(View.VISIBLE);
                    isScrolling=false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            for (int i =0 ; i<8;i++){
                                AnotherMovies.add(movies1[new Random().nextInt(4)]);
                                if (AnotherMovies.size()>3){
                                    AnotherMovies.get(AnotherMovies.size()-1).setId(AnotherMovies.get(AnotherMovies.size()-2).getId()+1);
                                }
                            }
                            movieAdapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                    },2500);
                }
            }
        });
    }
    private void actions(){
        givingLike();
    }
    private void givingLike(){
        LinearLayout likeBtn = (LinearLayout) findViewById(R.id.like_btnFather);
        ImageView heart=(ImageView) findViewById(R.id.heart_image);
        TextView text=(TextView) findViewById(R.id.heart_text);
        likeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoviesTable db= new MoviesTable(show_details_activity.this);
                if (text.getText().equals("Like")){
                    ArrayList<Movie> movieArrayList=db.getData();
                    if (movieArrayList.size() ==0){
                        mainMovie.setId(1);
                    }else {
                        mainMovie.setId(movieArrayList.get(movieArrayList.size()-1).getId()+1);
                    }
                    String h=db.insert(mainMovie);
                    Toast.makeText(show_details_activity.this,h,Toast.LENGTH_SHORT).show();
                    heart.setImageResource(R.drawable.ic_baseline_favorite_24);
                    text.setText("Liked");
                }else {
                    text.setText("Like");
                    heart.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                    db.delete(mainMovie.getId());
                    Toast.makeText(show_details_activity.this,"Removed",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (list.size()==1){
            Intent i = this.getIntent();
            Intent i1 =null;
            if (i!=null){
                if (i.getStringExtra("sender")!= null &&i.getStringExtra("sender").equals("home")){
                    i1 = new Intent(this, HomeActivity.class);
                }else if (i.getStringExtra("sender")!= null && i.getStringExtra("sender").equals("search")){
                    i1 = new Intent(this, SearchActivity.class);
                }else if(i.getStringExtra("sender")!= null && i.getStringExtra("sender").equals("favourite")){
                    i1 = new Intent(this, FavouriteActivity.class);
                }else {
                    i1 = new Intent(this, HomeActivity.class);
                }
                startActivity(i1);
                overridePendingTransition(0, 0);
            }
        }else {
            list.remove(list.size()-1);
            mainMovie=list.get(list.size()-1);
            setUp(mainMovie);

        }
    }
    private void isInLiked(Movie m){
        boolean t=false;
        MoviesTable db= new MoviesTable(show_details_activity.this);
        ImageView heart=(ImageView) findViewById(R.id.heart_image);
        TextView text=(TextView) findViewById(R.id.heart_text);
        ArrayList<Movie> movieArrayList=db.getData();
        for (int i =0 ; i<movieArrayList.size();i++){
            if (m.getId() == movieArrayList.get(i).getId() && m.getTitle().equals(movieArrayList.get(i).getTitle())){
                heart.setImageResource(R.drawable.ic_baseline_favorite_24);
                text.setText("Liked");
                t=true;
                break;
            }
        }
        if (!t){
            heart.setImageResource(R.drawable.ic_baseline_favorite_border_24);
            text.setText("Like");
        }
    }

    public static Movie getMainMovie() {
        return mainMovie;
    }
}