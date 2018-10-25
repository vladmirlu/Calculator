package com.math.calculator.history;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class HistoryPrintWriter {

    private List<User> users = new ArrayList<>();

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


    public void printResults() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (User user : users) {
            System.out.println("User #" + user.getId() + "  name: " + user.getUsername());
            System.out.println("Calculation history:");
            List<CalcResult> results = user.getCalcResults();
            for (int i = 0; i < results.size(); i++) {
                System.out.println("Result " + i+1 + "):  " + results.get(i).getEnteredData() + " = " + results.get(i).getResult() + " Calculated " + results.get(i).getDateTime().format(formatter));
            }
        }
    }
}
