package com.example.myactivities;

import androidx.annotation.IdRes;

public class FrameActivity extends BaseNavigationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.frame_layout;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.frameFragment;
    }
}
