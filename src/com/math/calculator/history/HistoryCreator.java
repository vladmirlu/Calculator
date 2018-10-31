package com.math.calculator.history;

import java.util.List;

public class HistoryCreator {

    private final List<User> users;

   public HistoryCreator (List<User> users) {
       this.users = users;
   }

    public void createNewNote(String username, String data, Double result) {
        if (!users.isEmpty()) {
            for (User user : users) {
                if (user.getUsername().equals(username)) {
                    user.getCalcResults().add(new CalcResult(data, result));
                } else {
                    User newUser = new User(username, new CalcResult(data, result));
                    users.add(newUser);
                    break;
                }
            }
        } else users.add(new User(username, new CalcResult(data, result)));
    }
}
