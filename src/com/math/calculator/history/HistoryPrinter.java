package com.math.calculator.history;

import com.math.calculator.history.model.CalcResult;
import com.math.calculator.history.model.User;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HistoryPrinter {

    private final List<User> users;

   public final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public HistoryPrinter(List<User> users){
        this.users = users;
    }

    public void printAllResults() {
        for (User user : users) {
            System.out.println("User #" + user.getId() + "  name: " + user.getUsername() + "\n Calculation history:");
            user.getCalcResults().forEach(res->System.out.println("Result: " + res.getEnteredData() + " = " + res.getResult() + " Date Time: " + res.getDateTime().format(FORMATTER)));
        }
    }
    public void printAllUniqueResults(){
        CalcResult [] results = users.stream().flatMap(user->user.getCalcResults().stream()).toArray(CalcResult[]::new);
        HashMap<Double,Double> map = new HashMap<>(results.length);
        for(CalcResult num : results) {
            Double occurrence = map.get(num.getResult());
            map.put(num.getResult(), occurrence==null ? 1 : occurrence + 1);
        }
        for(Map.Entry<Double,Double> e : map.entrySet()) {
            if(e.getValue() == 1) {
                System.out.println("Unique result = " + e.getKey());
            }
        }
    }
}
