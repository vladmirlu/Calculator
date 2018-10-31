package com.math.calculator.calculation;



import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.exception.WrongDataQuantityException;
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

    private Double getCalcResult(String expression) throws WrongDataQuantityException, OperatorNotFoundException {

        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
               String temp = tokenizer.nextToken().trim();
                if (temp.length() == 1 && validator.isOperator(Character.toString(temp.charAt(0)))) {
                    Operator operator = validator.getOperator(Character.toString(temp.charAt(0)));
                    if (stack.size() < 2) {
                        throw new WrongDataQuantityException();
                    }
                    double number = stack.pop();
                    stack.push(operator.apply(stack.pop(), number));
                } else {
                    stack.push(Double.parseDouble(temp));
                }
        }
        if (stack.size() > 1) throw new WrongDataQuantityException();
        return stack.pop();
    }

    public List<User> getUsers() {
        return users;
    }

    public Validator getValidator() {
        return validator;
    }
}
