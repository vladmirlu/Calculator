package com.math.calculator.calculation;


import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.exception.WrongDataQuantityException;
import com.math.calculator.calculation.symbols.Operator;
import com.math.calculator.history.HistoryCreator;
import com.math.calculator.history.model.User;

import java.util.*;

/**
 * Calculates and calls MathStringBuilder and HistoryCreator
 */
public class MathService {

    private final List<User> users = new ArrayList<>();

    private final MathValidator validator = new MathValidator();

    private final MathStringBuilder mathStringBuilder = new MathStringBuilder(validator);

    private final HistoryCreator historyCreator = new HistoryCreator(users);

    /**
     * Calls MathStringBuilder and HistoryCreator and returns result if the string is valid
     * @param expression entered math expression
     * @param username entered user name
     * @return calculation result as string or error message
     */
    public String calculateAndSave(String expression, String username) {

        if (validator.isValidExpression(expression)) {
            try {
                String orderedExp = mathStringBuilder.buildOrderedMathString(expression);
                Double result = calculate(orderedExp);
                historyCreator.createNewNote(username, expression, result);
                return result.toString();
            } catch (OperatorNotFoundException o) {
                o.printStackTrace();
                return "Error! Operator not found";
            } catch (WrongDataQuantityException w) {
                w.printStackTrace();
                return "Error! Wrong data quantity";
            }
        }
        return "Error! Invalid math expression";
    }

    /**
     * Calculates and returns the result
     * @param expression string of math elements in priority order
     * @return result of calculation
     * @throws OperatorNotFoundException when operator symbol is not operator
     * @throws WrongDataQuantityException when quantity operators doesn't match quantity of operands
     */
    private Double calculate(String expression) throws WrongDataQuantityException, OperatorNotFoundException {

        Deque<Double> numbers = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(expression);
        while (tokenizer.hasMoreTokens()) {
            String nextToken = tokenizer.nextToken().trim();
            if (nextToken.length() == 1 && validator.isOperator(Character.toString(nextToken.charAt(0)))) {
                if (numbers.size() < 2) {
                    throw new WrongDataQuantityException();
                }
                Operator operator = validator.findOperator(Character.toString(nextToken.charAt(0)));
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
     * @return  users list
     */
    public List<User> getUsers() {
        return users;
    }

}
