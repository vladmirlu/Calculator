package com.math.calculator.history;

import com.math.calculator.calculation.exception.UserNotFoundException;
import com.math.calculator.history.model.CalcResult;
import com.math.calculator.history.model.User;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Shows calculation history
 */
public class HistoryPrinter {

    private final List<User> users;

    public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public HistoryPrinter(List<User> users) {
        this.users = users;
    }

    /**
     * Prints history of all results
     */
    public void printAllResults() {
        users.forEach(user -> System.out.println(getBuiltAllResultString(user)));
    }

    /**
     * Prints history of unique results only
     */
    public void printAllUniqueResults() {
        List<CalcResult> results = users.stream().flatMap(user -> user.getCalcResults().stream()).collect(Collectors.toList());
        System.out.println("All unique results " + getBuiltUniqueResultsString(results));
    }

    /**
     * Returns history of one user all results
     */
    public String getUserAllResults(String username) throws Exception {
        List<User> realUsers = users.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (!realUsers.isEmpty()) {
            return getBuiltAllResultString(realUsers.get(0));
        }
        throw new UserNotFoundException();
    }

    /**
     * Returns history of one user only unique results
     */
    public String getUserUniqueResults(String username) throws Exception {
        List<User> realUsers = users.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (!realUsers.isEmpty()) {
            return "All unique results of user " + username + getBuiltUniqueResultsString(realUsers.get(0).getCalcResults());
        }
        throw new UserNotFoundException();
    }

    /**
     * Builds the string of all results history
     */
    String getBuiltAllResultString(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("User #").append(user.getId()).append("  name: ").append(user.getUsername()).append("\n Calculation history:");
        user.getCalcResults().forEach(res -> builder.append("\nResult: ").append(res.getEnteredData()).append(" = ").append(res.getResult()).append(" Date Time: ").append(res.getDateTime().format(FORMATTER)));
        return builder.toString();
    }

    /**
     * Builds the string of unique results history
     */
    String getBuiltUniqueResultsString(List<CalcResult> results) {
        StringBuilder builder = new StringBuilder();
        HashMap<Double, Double> map = new HashMap<>(results.size());
        for (CalcResult result : results) {
            map.put(result.getResult(), map.get(result.getResult()) == null ? 1 : map.get(result.getResult()) + 1);
        }
        map.entrySet().stream().filter(e -> e.getValue() == 1).forEach(e -> builder.append("\n Unique result = ").append(e.getKey()));
        if(!builder.toString().equals("")) {
            return builder.toString();
        }
        else return "\n Unique results not found";
    }

}
