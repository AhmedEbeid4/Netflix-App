package com.example.netflix;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

//SQLITE database that will store users data
public class userTable extends SQLiteOpenHelper {
    public userTable( Context context) {
        super(context, "users.dp", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(id INT PRIMARY KEY , name VARCHAR(20) NOT NULL, email VARCHAR(30) NOT NULL , phone VARCHAR(15) NOT NULL,password VAECHAR(10) NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
    }
    public String insert(User a){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues c_v= new ContentValues();
        c_v.put("id",a.getId());
        c_v.put("name",a.getName());
        c_v.put("email",a.getEmail());
        c_v.put("phone",a.getPhone());
        c_v.put("password",a.getPassword());
        long h = database.insert("users",null,c_v);
        if (h != -1){
            return "Successful operation";
        }else {
            return "FAILED";
        }
    }
    public ArrayList<User> getData(){
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM users",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            users.add(new User(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3),
                    c.getString(4)
            ));
            c.moveToNext();
        }
        return users;
    }

    public ArrayList<Integer> getIDS(){
        ArrayList<Integer> integers = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor c = database.rawQuery("SELECT * FROM users",null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            integers.add(c.getInt(0));
            c.moveToNext();
        }
        return integers;
    }
}
