package com.math.calculator.history.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity to keep entered data and result of calculation
 */
public class CalcResult {

    private Long id;

    private String enteredData;

    private Double result;

    private LocalDateTime dateTime;

    public CalcResult(String enteredData, Double result) {
        this.enteredData = enteredData;
        this.result = result;
        this.dateTime = LocalDateTime.now();
        id = UUID.randomUUID().getMostSignificantBits();
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
