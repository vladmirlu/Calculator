package com.math.calculator.history;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class HistoryPrinter {

    private final List<User> users;

    public HistoryPrinter(List<User> users){
        this.users = users;
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
