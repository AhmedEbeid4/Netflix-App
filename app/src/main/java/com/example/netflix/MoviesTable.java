package com.example.netflix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MoviesTable extends SQLiteOpenHelper {
    public MoviesTable(Context context){
        super(context, "movies.dp", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE movies(title VARCHAR(30) NOT NULL, rate REAL NOT NULL, poster_path INTEGER NOT NULL,id INTEGER PRIMARY KEY ,overview TEXT NOT NULL , video_resource TEXT NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
    }
    public String insert(Movie a){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues c_v= new ContentValues();
        c_v.put("title",a.getTitle());
        c_v.put("rate",a.getPopularity());
        c_v.put("poster_path",a.getPosterPath());
        c_v.put("id",a.getId());
        c_v.put("overview",a.getDesc());
        c_v.put("video_resource",a.getVideoSource());
        long h = database.insert("movies",null,c_v);
        if (h != -1){
            return "Successful operation";
        }else {
            return "FAILED";
        }
    }
    public ArrayList<Movie> getData(){
        ArrayList<Movie> movies = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM movies",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Movie movie=new Movie(
                    c.getString(0),
                    c.getDouble(1),
                    c.getInt(2),
                    c.getInt(3),
                    c.getString(4)
            );
            movie.setVideoSource(c.getString(5));
            movies.add(movie);
            c.moveToNext();
        }
        return movies;
    }
    public void delete(int id){
        SQLiteDatabase db= this.getWritableDatabase();
        db.delete("movies","id=?",new String[]{String.valueOf(id)});
    }
}
