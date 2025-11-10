package com.example.myactivities;

/**
 * Константы для ключей Intent
 */
public class IntentConstants {
    // Ключи для передачи данных пользователя
    public static final String EXTRA_USER_NAME = "user_name";
    public static final String EXTRA_USER_AGE = "user_age";
    
    // Ключи для результата из SecondActivity
    public static final String RESULT_NAME = "result_name";
    public static final String RESULT_AGE = "result_age";
    public static final String RESULT_MESSAGE = "result_message";
    
    private IntentConstants() {
        // Приватный конструктор для предотвращения создания экземпляров
    }
}

