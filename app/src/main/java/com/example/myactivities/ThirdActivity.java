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
        String userName = getIntent().getStringExtra("user_name");
        int userAge = getIntent().getIntExtra("user_age", 0);

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText("Пользователь: " + userName + ", Возраст: " + userAge);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            finish(); // Просто закрываем Activity
        });
    }
}
