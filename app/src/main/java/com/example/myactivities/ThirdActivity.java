package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        String userName = "Гость";
        int userAge = 0;
        
        if (getIntent() != null) {
            userName = getIntent().getStringExtra(IntentConstants.EXTRA_USER_NAME);
            userAge = getIntent().getIntExtra(IntentConstants.EXTRA_USER_AGE, 0);
            if (userName == null || userName.isEmpty()) {
                userName = "Гость";
            }
        }

        TextView tvInfo = findViewById(R.id.tv_info);
        tvInfo.setText("Пользователь: " + userName + ", Возраст: " + userAge);

        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(v -> {
            if (getCallingActivity() != null) {
                finish();
            } else {
                // Если открыто из drawer, возвращаемся в SecondActivity
                Intent secondIntent = new Intent(ThirdActivity.this, SecondActivity.class);
                secondIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(secondIntent);
                finish();
            }
        });
    }
}
