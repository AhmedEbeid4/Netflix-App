package com.example.netflix;

import android.content.Intent;

import java.util.ArrayList;

public class Actors {
    private String name;
    private Integer imageResource;

    public Actors(String name, Integer imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageResource() {
        return imageResource;
    }

    public void setImageResource(Integer imageResource) {
        this.imageResource = imageResource;
    }
}
