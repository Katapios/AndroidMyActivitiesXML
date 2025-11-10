package com.example.myactivities;

import androidx.annotation.IdRes;

public class LayoutActivity extends BaseNavigationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.linear_layout;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.layoutFragment;
    }
}

