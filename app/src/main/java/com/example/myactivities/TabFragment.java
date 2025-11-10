package com.example.myactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class TabFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;
    private TabPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);

        adapter = new TabPagerAdapter(requireActivity());
        viewPager.setAdapter(adapter);

        String[] tabTitles = {"Вкладка 1", "Вкладка 2", "Вкладка 3"};
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> 
            tab.setText(tabTitles[position])
        ).attach();
    }
}

