package com.example.myactivities;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class TabPagerAdapter extends FragmentStateAdapter {

    public TabPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) return new Tab1Fragment();
        if (position == 1) return new Tab2Fragment();
        return new Tab3Fragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}

