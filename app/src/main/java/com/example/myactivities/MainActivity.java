package com.example.myactivities;

import android.os.Bundle;

import androidx.annotation.IdRes;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends BaseNavigationActivity {

    private NavController navController;
    private ResultViewModel resultViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        resultViewModel = new ViewModelProvider(this).get(ResultViewModel.class);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.mainFragment;
    }

    @Override
    protected void setupNavigation() {
        if (drawerLayout != null && toolbar != null) {
            androidx.appcompat.app.ActionBarDrawerToggle drawerToggle = 
                    new androidx.appcompat.app.ActionBarDrawerToggle(
                            this, drawerLayout, toolbar,
                            R.string.navigation_drawer_open,
                            R.string.navigation_drawer_close
                    );
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
        }
        
        android.view.View navHostView = findViewById(R.id.nav_host_fragment);
        if (navHostView == null) {
            return;
        }
        
        navController = Navigation.findNavController(navHostView);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.mainFragment, R.id.layoutFragment, R.id.frameFragment, R.id.tableFragment)
                .setDrawerLayout(drawerLayout)
                .build();
        
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        
        if (bottomNavigationView != null) {
            NavigationUI.setupWithNavController(bottomNavigationView, navController);
        }
        
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.nav_drawer_second) {
                    startActivity(new android.content.Intent(this, SecondActivity.class));
                    drawerLayout.closeDrawers();
                    return true;
                } else if (itemId == R.id.nav_drawer_third) {
                    startActivity(createThirdActivityIntent("Из Drawer Menu", 30));
                    drawerLayout.closeDrawers();
                    return true;
                }
                return NavigationUI.onNavDestinationSelected(item, navController);
            });
            
            android.view.MenuItem menuItem = navigationView.getMenu().findItem(R.id.mainFragment);
            if (menuItem != null) {
                menuItem.setChecked(true);
            }
        }
    }
    
    public ResultViewModel getResultViewModel() {
        return resultViewModel;
    }
}
