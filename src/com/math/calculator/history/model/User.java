package com.math.calculator.history.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User that have his own calculation history
 */
public class User {

    private Long id;

    private String username;

    private final List<CalcResult> calcResults;

    public User(String username, CalcResult result) {
        id = UUID.randomUUID().getMostSignificantBits();
        this.username = username;
        calcResults = new ArrayList<>();
        calcResults.add(result);
    }


    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<CalcResult> getCalcResults() {
        return calcResults;
    }
}
