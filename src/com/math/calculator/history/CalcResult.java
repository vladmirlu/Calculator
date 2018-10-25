package com.math.calculator.history;

import java.time.LocalDateTime;

public class CalcResult {

    private String enteredData;

    private Double result;

    private LocalDateTime dateTime;

    public CalcResult(String enteredData, Double result) {
        this.enteredData = enteredData;
        this.result = result;
        this.dateTime = LocalDateTime.now();
    }

    public String getEnteredData() {
        return enteredData;
    }

    public Double getResult() {
        return result;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
