package com.example.myactivities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public abstract class BaseNavigationActivity extends AppCompatActivity {

    protected BottomNavigationView bottomNavigationView;
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());

        // Настройка Toolbar
        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        if (drawerLayout != null && navigationView != null && toolbar != null) {
            setupDrawerNavigation();
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {
            setupBottomNavigation();
        }
    }

    private void setupDrawerNavigation() {
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(this::onDrawerNavigationItemSelected);
        
        MenuItem menuItem = navigationView.getMenu().findItem(getSelectedDrawerMenuItemId());
        if (menuItem != null) {
            menuItem.setChecked(true);
        }
    }

    private boolean onDrawerNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        
        if (itemId == getSelectedDrawerMenuItemId()) {
            drawerLayout.closeDrawers();
            return true;
        }
        
        android.content.Intent intent = null;
        if (itemId == R.id.nav_drawer_main) {
            intent = new android.content.Intent(this, MainActivity.class);
        } else if (itemId == R.id.nav_drawer_layout) {
            intent = new android.content.Intent(this, LayoutActivity.class);
        } else if (itemId == R.id.nav_drawer_frame) {
            intent = new android.content.Intent(this, FrameActivity.class);
        } else if (itemId == R.id.nav_drawer_table) {
            intent = new android.content.Intent(this, TableActivity.class);
        } else if (itemId == R.id.nav_drawer_second) {
            intent = new android.content.Intent(this, SecondActivity.class);
        } else if (itemId == R.id.nav_drawer_third) {
            intent = createThirdActivityIntent("Из Drawer Menu", 30);
        }
        
        if (intent != null) {
            startActivity(intent);
            drawerLayout.closeDrawers();
            finish();
            return true;
        }
        
        return false;
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == getSelectedNavigationItemId()) {
                return true;
            }
            
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

    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getSelectedNavigationItemId();

    @IdRes
    protected int getSelectedDrawerMenuItemId() {
        int bottomNavId = getSelectedNavigationItemId();
        if (bottomNavId == R.id.nav_main) {
            return R.id.nav_drawer_main;
        } else if (bottomNavId == R.id.nav_layout) {
            return R.id.nav_drawer_layout;
        } else if (bottomNavId == R.id.nav_frame) {
            return R.id.nav_drawer_frame;
        } else if (bottomNavId == R.id.nav_table) {
            return R.id.nav_drawer_table;
        }
        return R.id.nav_drawer_main;
    }

    protected android.content.Intent createThirdActivityIntent(String userName, int userAge) {
        android.content.Intent intent = new android.content.Intent(this, ThirdActivity.class);
        intent.putExtra(IntentConstants.EXTRA_USER_NAME, userName);
        intent.putExtra(IntentConstants.EXTRA_USER_AGE, userAge);
        return intent;
    }
}

