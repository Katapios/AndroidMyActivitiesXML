package com.example.myactivities;

import androidx.annotation.IdRes;

public class TableActivity extends BaseNavigationActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.table_layout;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.tableFragment;
    }
}
