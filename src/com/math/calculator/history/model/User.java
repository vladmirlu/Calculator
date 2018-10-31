package com.math.calculator.history.model;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class User {

    private Long id;

    private String username;

    private List<CalcResult> calcResults;

   public User(String username, CalcResult result) {
        id =  UUID.randomUUID().getMostSignificantBits();
        this.username = username;
        calcResults = Collections.singletonList(result);
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
