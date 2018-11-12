package com.math.calculator.history.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity to keep entered data and result of calculation
 */
public class CalcResult {

    /**
     * Id to keep the entity
     */
    private Long id;

    /**
     * Math expression for further calculation
     */
    private String mathExpression;

    /**
     * The result of calculation @mathExpression
     */
    private Double result;

    /**
     * Date and time of calculation
     */
    private LocalDateTime dateTime;

    /**
     * Constructor to build entity of calculation process for saving in history
     *
     * @param expression math expression for further calculation
     * @param result     the result of math calculation
     */
    public CalcResult(String expression, Double result) {
        mathExpression = expression;
        this.result = result;
        this.dateTime = LocalDateTime.now();
        id = UUID.randomUUID().getMostSignificantBits();
    }

    public String getMathExpression() {
        return mathExpression;
    }

    public Double getResult() {
        return result;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
