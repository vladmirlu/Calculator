package com.math.calculator.history;

import com.math.calculator.history.model.CalcResult;
import com.math.calculator.history.model.User;

import java.util.List;

/**
 * Creates history
 */
public class HistoryCreator {

    /**
     *  users list for creation history
     */
    private final List<User> users;

    /**
     * Build the service for history creation
     *
     * @param users list of all existing users
     */
    public HistoryCreator(List<User> users) {
        this.users = users;
    }

    /**
     * Creates new Note in history
     *
     * @param username entered user name
     * @param data     entered math expression
     * @param result   result of calculation
     */
    public void createNewNote(String username, String data, Double result) {
        boolean userNotFound = false;
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    user.getCalcResults().add(new CalcResult(data, result));
                    break;
                } else userNotFound = true;
            }
        }
        if (users.isEmpty() || userNotFound) users.add(new User(username, new CalcResult(data, result)));
    }
}
