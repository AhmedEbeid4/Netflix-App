package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {
    private int position;
    private int or;
    private String sender;
    private String s;
    private Intent i;
    private static Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        s=this.getIntent().getStringExtra("s");
        if (s!= null && s.equals("sh")){
            position=this.getIntent().getIntExtra("index",0);
            or=this.getIntent().getIntExtra("1or2",-1);
            sender=this.getIntent().getStringExtra("sender");
            i = new Intent(this,show_details_activity.class);
            movie=show_details_activity.getMainMovie();
        }else {
            i=new Intent(this,HomeActivity.class);
            movie=HomeActivity.getMovie();
        }
        VideoView v = (VideoView) findViewById(R.id.videoView);
        v.setVideoPath(movie.getVideoSource());
        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(v);
        v.setMediaController(mediaController);
        v.start();
    }

    @Override
    public void onBackPressed() {

        if (s!= null && s.equals("sh")){
            i.putExtra("index", position);
            if (or != -1){
                i.putExtra("1or2",or);
            }
            i.putExtra("sender", sender);
            i.putExtra("dd","video");
        }
        startActivity(i);
        overridePendingTransition(0, 0);
    }
    public static Movie getMovie(){
        return movie;
    }
}