package com.example.netflix;

import java.util.ArrayList;

public class Movie {
    private String title;
    private double rate;
    private int posterPath;
    private Integer id;
    private String desc;
    private ArrayList<Actors> actors;
    private String videoSource;

    public Movie(String title, double rate, int posterPath, Integer id, String desc, ArrayList<Actors> actors, String videoSource) {
        this.title = title;
        this.rate = rate;
        this.posterPath = posterPath;
        this.id = id;
        this.desc = desc;
        this.actors = actors;
        this.videoSource = videoSource;
    }

    public String getVideoSource() {
        return videoSource;
    }

    public void setVideoSource(String videoSource) {
        this.videoSource = videoSource;
    }

    public Movie(String title, double popularity, int posterPath, Integer id, String desc, ArrayList<Actors> actors) {
        this.title=title;
        this.rate = popularity;
        this.actors=actors;
        this.posterPath = posterPath;
        this.id = id;
        this.desc=desc;
    }
    public Movie(String title,double popularity, int posterPath, Integer id,String desc) {
        this.title=title;
        this.rate = popularity;

        this.posterPath = posterPath;
        this.id = id;
        this.desc=desc;
    }

    public ArrayList<Actors> getActors() {
        return actors;
    }

    public void setActors(ArrayList<Actors> actors) {
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return rate;
    }

    public Movie() {
    }

    public void setPopularity(double popularity) {
        this.rate = popularity;
    }


    public Integer getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(int posterPath) {
        this.posterPath = posterPath;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
