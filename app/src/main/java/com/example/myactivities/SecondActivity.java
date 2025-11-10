package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    
    private EditText etUserName;
    private EditText etUserAge;
    private EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etUserName = findViewById(R.id.et_user_name);
        etUserAge = findViewById(R.id.et_user_age);
        etMessage = findViewById(R.id.et_message);

        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra(IntentConstants.EXTRA_USER_NAME);
            int userAge = intent.getIntExtra(IntentConstants.EXTRA_USER_AGE, 0);
            
            if (userName != null && !userName.isEmpty()) {
                etUserName.setText(userName);
            }
            if (userAge > 0) {
                etUserAge.setText(String.valueOf(userAge));
            }
        }

        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String ageText = etUserAge.getText().toString().trim();
            
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(this, "Пожалуйста, введите имя", Toast.LENGTH_SHORT).show();
                return;
            }
            
            if (TextUtils.isEmpty(ageText)) {
                Toast.makeText(this, "Пожалуйста, введите возраст", Toast.LENGTH_SHORT).show();
                return;
            }
            
            try {
                int age = Integer.parseInt(ageText);
                if (age <= 0 || age > 150) {
                    Toast.makeText(this, "Возраст должен быть от 1 до 150", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                if (getCallingActivity() != null) {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra(IntentConstants.RESULT_NAME, name);
                    resultIntent.putExtra(IntentConstants.RESULT_AGE, age);
                    
                    String message = etMessage.getText().toString().trim();
                    if (!TextUtils.isEmpty(message)) {
                        resultIntent.putExtra(IntentConstants.RESULT_MESSAGE, message);
                    }
                    
                    setResult(RESULT_OK, resultIntent);
                    finish();
                } else {
                    // Если открыто из drawer, возвращаемся на MainActivity
                    Intent mainIntent = new Intent(SecondActivity.this, MainActivity.class);
                    mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(mainIntent);
                    finish();
                }
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Возраст должен быть числом", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            if (getCallingActivity() != null) {
                setResult(RESULT_CANCELED);
                finish();
            } else {
                // Если открыто из drawer, возвращаемся на MainActivity
                Intent mainIntent = new Intent(SecondActivity.this, MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(mainIntent);
                finish();
            }
        });

        Button btnOpenThird = findViewById(R.id.btn_open_third);
        btnOpenThird.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String ageText = etUserAge.getText().toString().trim();
            
            int age = 25;
            if (!TextUtils.isEmpty(ageText)) {
                try {
                    age = Integer.parseInt(ageText);
                } catch (NumberFormatException e) {
                    age = 25;
                }
            }
            
            Intent intentToThird = new Intent(SecondActivity.this, ThirdActivity.class);
            intentToThird.putExtra(IntentConstants.EXTRA_USER_NAME, TextUtils.isEmpty(name) ? "Иван Иванов" : name);
            intentToThird.putExtra(IntentConstants.EXTRA_USER_AGE, age);
            startActivity(intentToThird);
        });
    }
}