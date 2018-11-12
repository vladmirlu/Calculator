package com.math.calculator.history.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * User that have his own calculation history
 */
public class User {

    /**
     * Id to keep the entity
     */
    private Long id;

    /**
     * username of exact user
     */
    private String username;

    /**Entity to keep both math expression and the result of calculation*/
    private final List<CalcResult> calcResults;

    /**Constructor to build user entity for saving in history
     *
     * @param username username of concrete user
     * @param result entity to keep calculation history
     * */
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
