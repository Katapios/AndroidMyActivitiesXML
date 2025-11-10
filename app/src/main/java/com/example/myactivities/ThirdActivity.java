package com.example.myactivities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Получение данных из Intent
        String userName = "Гость";
        int userAge = 0;
        
        if (getIntent() != null) {
            userName = getIntent().getStringExtra("user_name");
            userAge = getIntent().getIntExtra("user_age", 0);
            
            if (userName == null || userName.isEmpty()) {
                userName = "Гость";
            }
        }

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText("Пользователь: " + userName + ", Возраст: " + userAge);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish(); // Просто закрываем Activity
        });
    }
}
