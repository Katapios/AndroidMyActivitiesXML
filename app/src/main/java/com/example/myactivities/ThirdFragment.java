package com.example.myactivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ThirdFragment extends Fragment {
    
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_third, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        String userName = (args != null && args.getString("user_name") != null) 
                ? args.getString("user_name") : "Гость";
        int userAge = (args != null) ? args.getInt("user_age", 0) : 0;

        TextView tvInfo = view.findViewById(R.id.tv_info);
        tvInfo.setText("Пользователь: " + userName + ", Возраст: " + userAge);

        Button btnBack = view.findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> Navigation.findNavController(v).navigateUp());
    }
}

