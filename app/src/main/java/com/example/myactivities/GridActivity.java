package com.example.myactivities;

import androidx.annotation.IdRes;

public class GridActivity extends BaseNavigationActivity{
    @Override
    protected int getLayoutResId() {
        return R.layout.frame_layout;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.nav_frame;
    }
}
