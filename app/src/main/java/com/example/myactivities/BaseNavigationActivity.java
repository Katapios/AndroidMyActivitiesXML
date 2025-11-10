package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseNavigationActivity extends AppCompatActivity 
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    protected BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(getSelectedNavigationItemId());
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        // Если уже на этой странице, ничего не делаем
        if (itemId == getSelectedNavigationItemId()) {
            return true;
        }
        
        // Определяем класс активити для перехода
        Class<? extends AppCompatActivity> targetActivity = getActivityClassForNavigationItem(itemId);
        
        if (targetActivity != null) {
            Intent intent = new Intent(this, targetActivity);
            startActivity(intent);
            finish();
            return true;
        }
        
        return false;
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

    /**
     * Возвращает класс активити для перехода по выбранному пункту меню
     */
    private Class<? extends AppCompatActivity> getActivityClassForNavigationItem(int itemId) {
        if (itemId == R.id.nav_main) {
            return MainActivity.class;
        } else if (itemId == R.id.nav_layout) {
            return LayoutActivity.class;
        } else if (itemId == R.id.nav_frame) {
            return FrameActivity.class;
        } else if (itemId == R.id.nav_table) {
            return TableActivity.class;
        }
        return null;
    }
}

