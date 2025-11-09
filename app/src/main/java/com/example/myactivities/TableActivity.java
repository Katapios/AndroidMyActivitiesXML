package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class TableActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table_layout);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.nav_table);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.nav_main) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.nav_layout) {
            Intent intent = new Intent(this, LayoutActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.nav_frame) {
            Intent intent = new Intent(this, FrameActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (itemId == R.id.nav_table) {
            // Уже на этой странице
            return true;
        }

        return false;
    }
}
