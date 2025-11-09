package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FrameActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frame_layout);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.nav_frame);
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
            // Уже на этой странице
            return true;
        } else if (itemId == R.id.nav_table) {
            Intent intent = new Intent(this, TableActivity.class);
            startActivity(intent);
            finish();
            return true;
        }
        
        return false;
    }
}
