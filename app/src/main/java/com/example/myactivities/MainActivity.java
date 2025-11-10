package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;

public class MainActivity extends BaseNavigationActivity {

    private static final int REQUEST_CODE = 1;
    private static final String EXTRA_USER_NAME = "user_name";
    private static final String EXTRA_USER_AGE = "user_age";
    
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnOpenSecond = findViewById(R.id.btn_open_second);
        btnOpenSecond.setOnClickListener(v -> {
            // Переход к ThirdActivity через Intent
            Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
            intent.putExtra("user_name", "Иван Иванов");
            intent.putExtra("user_age", 25);
            startActivity(intent);
        });

        Button btnOpenWithResult = findViewById(R.id.btn_open_with_result);
        btnOpenWithResult.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(EXTRA_USER_NAME, "Иван Иванов");
            intent.putExtra(EXTRA_USER_AGE, 25);
            startActivityForResult(intent, REQUEST_CODE);
        });

        tvResult = findViewById(R.id.tv_result);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    @IdRes
    protected int getSelectedNavigationItemId() {
        return R.id.nav_main;
    }

    // Обработка результата из SecondActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        // Добавляем логирование для отладки
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String resultName = data.getStringExtra("result_name");
                    int resultAge = data.getIntExtra("result_age", 0);
                    String resultMessage = data.getStringExtra("result_message");
                    
                    if (resultName != null && resultAge > 0) {
                        String displayText = String.format("Получены данные:\nИмя: %s\nВозраст: %d", resultName, resultAge);
                        if (resultMessage != null && !resultMessage.isEmpty()) {
                            displayText += "\nСообщение: " + resultMessage;
                        }
                        if (tvResult != null) {
                            tvResult.setText(displayText);
                        }
                        Toast.makeText(this, "Данные успешно получены!", Toast.LENGTH_SHORT).show();
                    } else if (resultMessage != null) {
                        if (tvResult != null) {
                            tvResult.setText("Результат: " + resultMessage);
                        }
                        Toast.makeText(this, resultMessage, Toast.LENGTH_SHORT).show();
                    } else {
                        // Если данные не получены, но результат OK
                        if (tvResult != null) {
                            tvResult.setText("Результат получен, но данные отсутствуют");
                        }
                        Toast.makeText(this, "Результат получен", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Результат OK, но data == null
                    if (tvResult != null) {
                        tvResult.setText("Результат получен, но данные отсутствуют");
                    }
                    Toast.makeText(this, "Результат получен без данных", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                if (tvResult != null) {
                    tvResult.setText("Операция отменена");
                }
                Toast.makeText(this, "Операция отменена", Toast.LENGTH_SHORT).show();
            } else {
                // Неизвестный результат
                if (tvResult != null) {
                    tvResult.setText("Неизвестный результат: " + resultCode);
                }
            }
        }
    }
}
