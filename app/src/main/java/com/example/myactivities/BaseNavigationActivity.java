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

        toolbar = findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        
        if (bottomNavigationView != null) {
            setupBottomNavigation();
        }
        
        setupNavigation();
        
        if (drawerLayout != null && navigationView != null && toolbar != null) {
            if (!(this instanceof MainActivity)) {
                setupDrawerNavigation();
            }
        }
    }
    
    protected void setupNavigation() {
        // Override in subclasses if needed
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
        
        android.content.Intent intent = null;
        if (itemId == R.id.nav_drawer_second) {
            intent = new android.content.Intent(this, SecondActivity.class);
        } else if (itemId == R.id.nav_drawer_third) {
            intent = createThirdActivityIntent("Из Drawer Menu", 30);
        }
        
        if (intent != null) {
            startActivity(intent);
            drawerLayout.closeDrawers();
            if (!(this instanceof MainActivity)) {
                finish();
            }
            return true;
        }
        
        if (this instanceof MainActivity) {
            drawerLayout.closeDrawers();
            return false;
        }
        
        if (itemId == getSelectedDrawerMenuItemId()) {
            drawerLayout.closeDrawers();
            return true;
        }
        
        return false;
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setSelectedItemId(getSelectedNavigationItemId());
    }

    protected abstract int getLayoutResId();

    @IdRes
    protected abstract int getSelectedNavigationItemId();

    @IdRes
    protected int getSelectedDrawerMenuItemId() {
        return getSelectedNavigationItemId();
    }

    protected android.content.Intent createThirdActivityIntent(String userName, int userAge) {
        android.content.Intent intent = new android.content.Intent(this, ThirdActivity.class);
        intent.putExtra(IntentConstants.EXTRA_USER_NAME, userName);
        intent.putExtra(IntentConstants.EXTRA_USER_AGE, userAge);
        return intent;
    }
}

