package com.math.calculator.calculation;



import com.math.calculator.calculation.symbols.Operator;
import com.math.calculator.history.HistoryCreator;
import com.math.calculator.history.model.User;

import java.util.*;

public class Service {

    private final List<User> users = new ArrayList<>();

    private final Validator validator = new Validator();

    private final Decorator decorator = new Decorator(validator);

    private final HistoryCreator historyCreator = new HistoryCreator(users);

    public String calculate(String expression, String username) throws  Exception{
        expression = decorator.decorate(expression);
        Double result = getCalcResult(expression);
        historyCreator.createNewNote(username, expression, result);
        return result.toString();
    }

    private Double getCalcResult(String expression) throws Exception {
        double numb1, numb2;
        String temp;
        Operator operator;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            try {
                temp = tokenizer.nextToken().trim();
                if (temp.length() == 1 && validator.isOperator(Character.toString(temp.charAt(0)))) {
                    operator = validator.getOperator(Character.toString(temp.charAt(0)));
                    if (stack.size() < 2) throw new Exception("Wrong data quantity" + temp);
                    numb2 = stack.pop();
                    numb1 = stack.pop();
                    numb1 = operator.apply(numb1, numb2);
                    stack.push(numb1);
                } else {
                    numb1 = Double.parseDouble(temp);
                    stack.push(numb1);
                }
            } catch (Exception e) {
                throw new Exception("Invalid math symbol");
            }
        }
        if (stack.size() > 1) throw new Exception("Operators quantity doesn't match operands quantity");
        return stack.pop();
    }

    public List<User> getUsers() {
        return users;
    }

    public Validator getValidator() {
        return validator;
    }
}
