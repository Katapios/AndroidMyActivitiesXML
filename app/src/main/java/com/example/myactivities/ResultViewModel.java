package com.example.myactivities;

import androidx.lifecycle.ViewModel;

public class ResultViewModel extends ViewModel {
    private String resultName;
    private int resultAge;
    private String resultMessage;
    private boolean resultCancelled;
    private boolean hasResult;

    public void saveResult(String name, int age, String message) {
        this.resultName = name;
        this.resultAge = age;
        this.resultMessage = message;
        this.resultCancelled = false;
        this.hasResult = true;
    }

    public void saveCancelResult() {
        this.resultCancelled = true;
        this.hasResult = true;
    }

    public void clearResult() {
        hasResult = false;
        resultName = null;
        resultAge = 0;
        resultMessage = null;
        resultCancelled = false;
    }

    public boolean hasResult() {
        return hasResult;
    }

    public String getResultName() {
        return resultName;
    }

    public int getResultAge() {
        return resultAge;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public boolean isResultCancelled() {
        return resultCancelled;
    }
}

