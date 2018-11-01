package com.math.calculator.calculation;


import com.math.calculator.calculation.exception.InvalidMathExpressionException;
import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.exception.WrongDataQuantityException;
import com.math.calculator.calculation.symbols.Operator;
import com.math.calculator.history.HistoryCreator;
import com.math.calculator.history.model.User;

import java.util.*;

/**
 * Calculates and calls MathDecorator and HistoryCreator
 */
public class MathService {

    private final List<User> users = new ArrayList<>();

    private final MathValidator validator = new MathValidator();

    private final MathDecorator mathDecorator = new MathDecorator(validator);

    private final HistoryCreator historyCreator = new HistoryCreator(users);

    /**
     * Calls MathDecorator and HistoryCreator and returns result if the string is valid
     */
    public String calculateAndSave(String expression, String username) throws Exception {

        if (validator.isValidExpression(expression)) {
            String orderedExp = mathDecorator.decorateAndBuild(expression);
            Double result = getCalcResult(orderedExp);
            historyCreator.createNewNote(username, expression, result);
            return result.toString();
        }
        throw new InvalidMathExpressionException();
    }

    /**
     * Calculates and returns the result
     */
    private Double getCalcResult(String expression) throws WrongDataQuantityException, OperatorNotFoundException {

        Deque<Double> numbers = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken().trim();
            if (nextToken.length() == 1 && validator.isOperator(Character.toString(nextToken.charAt(0)))) {
                if (numbers.size() < 2) {
                    throw new WrongDataQuantityException();
                }
                Operator operator = validator.getOperator(Character.toString(nextToken.charAt(0)));
                double number = numbers.pop();
                numbers.push(operator.apply(numbers.pop(), number));
            } else {
                numbers.push(Double.parseDouble(nextToken));
            }
        }
        if (numbers.size() > 1) throw new WrongDataQuantityException();
        return numbers.pop();
    }

    /**
     * Returns list of all users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Returns MathValidator
     */
    public MathValidator getValidator() {
        return validator;
    }
}
