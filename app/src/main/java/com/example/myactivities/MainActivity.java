package com.example.myactivities;

import androidx.annotation.IdRes;

public class MainActivity extends BaseNavigationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.nav_main;
    }
}
