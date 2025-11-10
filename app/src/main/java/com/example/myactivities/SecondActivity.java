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

        // Получение данных из Intent
        Intent intent = getIntent();
        if (intent != null) {
            String userName = intent.getStringExtra("user_name");
            int userAge = intent.getIntExtra("user_age", 0);
            
            if (userName != null && !userName.isEmpty()) {
                etUserName.setText(userName);
            }
            if (userAge > 0) {
                etUserAge.setText(String.valueOf(userAge));
            }
        }

        Button btnClose = findViewById(R.id.btn_close);
        btnClose.setOnClickListener(v -> {
            // Валидация данных
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
                
                // Возврат результата в MainActivity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("result_name", name);
                resultIntent.putExtra("result_age", age);
                
                String message = etMessage.getText().toString().trim();
                if (!TextUtils.isEmpty(message)) {
                    resultIntent.putExtra("result_message", message);
                }
                
                // Устанавливаем результат и закрываем Activity
                setResult(RESULT_OK, resultIntent);
                finish(); // Закрытие Activity
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Возраст должен быть числом", Toast.LENGTH_SHORT).show();
            }
        });

        Button btnCancel = findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(v -> {
            setResult(RESULT_CANCELED);
            finish();
        });

        Button btnOpenThird = findViewById(R.id.btn_open_third);
        btnOpenThird.setOnClickListener(v -> {
            String name = etUserName.getText().toString().trim();
            String ageText = etUserAge.getText().toString().trim();
            
            Intent intentToThird = new Intent(SecondActivity.this, ThirdActivity.class);
            if (!TextUtils.isEmpty(name)) {
                intentToThird.putExtra("user_name", name);
            } else {
                intentToThird.putExtra("user_name", "Иван Иванов");
            }
            
            if (!TextUtils.isEmpty(ageText)) {
                try {
                    intentToThird.putExtra("user_age", Integer.parseInt(ageText));
                } catch (NumberFormatException e) {
                    intentToThird.putExtra("user_age", 25);
                }
            } else {
                intentToThird.putExtra("user_age", 25);
            }
            startActivity(intentToThird);
        });
    }
}