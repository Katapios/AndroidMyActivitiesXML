package com.example.myactivities;

import android.os.Bundle;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseNavigationActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        // Настройка BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            setupBottomNavigation();
        }
    }

    /**
     * Настройка навигации через BottomNavigationView
     */
    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == getSelectedNavigationItemId()) {
                return true;
            }
            
            // Используем Intent для навигации
            android.content.Intent intent = null;
            if (itemId == R.id.nav_main) {
                intent = new android.content.Intent(this, MainActivity.class);
            } else if (itemId == R.id.nav_layout) {
                intent = new android.content.Intent(this, LayoutActivity.class);
            } else if (itemId == R.id.nav_frame) {
                intent = new android.content.Intent(this, FrameActivity.class);
            } else if (itemId == R.id.nav_table) {
                intent = new android.content.Intent(this, TableActivity.class);
            }
            
            if (intent != null) {
                startActivity(intent);
                finish();
                return true;
            }
            return false;
        });
        bottomNavigationView.setSelectedItemId(getSelectedNavigationItemId());
    }

    /**
     * Возвращает ID layout для активити
     */
    protected abstract int getLayoutResId();

    /**
     * Возвращает ID выбранного пункта навигации для текущей активити
     */
    @IdRes
    protected abstract int getSelectedNavigationItemId();
}

