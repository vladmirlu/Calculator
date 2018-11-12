package com.math.calculator.history;

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

    /**
     * list of all existing users
     */
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
        System.out.println("All unique results " + getUniqueResultsString(results));
    }

    /**
     * Returns history of one user all results
     *
     * @param username entered username
     * @return history of all calculation results of one user or user not found error message
     */
    public String getUserAllResults(String username) {
        List<User> realUsers = users.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (!realUsers.isEmpty()) {
            return getBuiltAllResultString(realUsers.get(0));
        }
        return " The user with username " + username + " not found";
    }

    /**
     * Returns history of one user only unique results
     *
     * @param username entered username
     * @return @return history of unique calculation results of one user or user not found error message
     */
    public String getUserUniqueResults(String username) {
        List<User> realUsers = users.stream().filter(u -> u.getUsername().equals(username)).collect(Collectors.toList());
        if (!realUsers.isEmpty()) {
            return "All unique results of user " + username + getUniqueResultsString(realUsers.get(0).getCalcResults());
        }
        return " The user with username " + username + " not found";
    }

    /**
     * Builds the string of all results history
     *
     * @param user user from list
     * @return string build of all user calculation
     */
    String getBuiltAllResultString(User user) {
        StringBuilder builder = new StringBuilder();
        builder.append("User #").append(user.getId()).append("  name: ").append(user.getUsername()).append("\n Calculation history:");
        user.getCalcResults().forEach(res -> builder.append("\nResult: ").append(res.getMathExpression()).append(" = ").append(res.getResult()).append(" Date Time: ").append(res.getDateTime().format(FORMATTER)));
        return builder.toString();
    }

    /**
     * Builds the string of unique results history
     *
     * @param results list of calculation results
     * @return string build of unique calculation results
     */
    String getUniqueResultsString(List<CalcResult> results) {
        StringBuilder builder = new StringBuilder();
        HashMap<Double, Double> map = new HashMap<>(results.size());
        for (CalcResult result : results) {
            map.put(result.getResult(), map.get(result.getResult()) == null ? 1 : map.get(result.getResult()) + 1);
        }
        map.entrySet().stream().filter(e -> e.getValue() == 1).forEach(e -> builder.append("\n Unique result = ").append(e.getKey()));
        if (!builder.toString().equals("")) {
            return builder.toString();
        } else return "\n Unique results not found";
    }

}
