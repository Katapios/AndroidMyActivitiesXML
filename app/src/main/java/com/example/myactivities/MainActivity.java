package com.example.myactivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;

public class MainActivity extends BaseNavigationActivity {

    private static final int REQUEST_CODE = 1;
    
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button btnOpenSecond = findViewById(R.id.btn_open_second);
        btnOpenSecond.setOnClickListener(v -> {
            Intent intent = createThirdActivityIntent("Иван Иванов", 25);
            startActivity(intent);
        });

        Button btnOpenWithResult = findViewById(R.id.btn_open_with_result);
        btnOpenWithResult.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.putExtra(IntentConstants.EXTRA_USER_NAME, "Иван Иванов");
            intent.putExtra(IntentConstants.EXTRA_USER_AGE, 25);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        
        if (requestCode != REQUEST_CODE || tvResult == null) {
            return;
        }
        
        if (resultCode == RESULT_OK && data != null) {
            String resultName = data.getStringExtra(IntentConstants.RESULT_NAME);
            int resultAge = data.getIntExtra(IntentConstants.RESULT_AGE, 0);
            String resultMessage = data.getStringExtra(IntentConstants.RESULT_MESSAGE);
            
            if (resultName != null && resultAge > 0) {
                String displayText = String.format("Получены данные:\nИмя: %s\nВозраст: %d", resultName, resultAge);
                if (resultMessage != null && !resultMessage.isEmpty()) {
                    displayText += "\nСообщение: " + resultMessage;
                }
                tvResult.setText(displayText);
                Toast.makeText(this, "Данные успешно получены!", Toast.LENGTH_SHORT).show();
            } else {
                tvResult.setText(resultMessage != null ? "Результат: " + resultMessage : "Данные отсутствуют");
            }
        } else if (resultCode == RESULT_CANCELED) {
            tvResult.setText("Операция отменена");
            Toast.makeText(this, "Операция отменена", Toast.LENGTH_SHORT).show();
        }
    }
}
