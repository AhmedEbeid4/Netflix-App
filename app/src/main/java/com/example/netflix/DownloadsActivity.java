package com.example.netflix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DownloadsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloads);
        getSupportActionBar().hide();
        BottomNavigationView bar =(BottomNavigationView) findViewById(R.id.bar);
        bar.setSelectedItemId(R.id.downloads);
        bar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    goToHome();
               } else if (item.getItemId() == R.id.favourite) {
                    goToFavouriteActivity();
                } else if (item.getItemId() == R.id.search) {
                    goToSearchActivity();
                } else {
                    Toast.makeText(DownloadsActivity.this,"You are in downloads",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private void goToHome() {
        Intent i = new Intent(this, HomeActivity.class);
        startActivity(i);
        overridePendingTransition(0, 0);
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
}