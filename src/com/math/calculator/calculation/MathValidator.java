package com.math.calculator.calculation;

import com.math.calculator.calculation.symbols.UserAction;
import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.exception.UserActionNotFoundException;
import com.math.calculator.calculation.symbols.Bracket;
import com.math.calculator.calculation.symbols.Operator;

import java.util.regex.Pattern;

/**
 * Validates expression and symbols
 */
public class MathValidator {

    /**
     * Checks if symbol is operator
     */
    boolean isOperator(String symbol) {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return true;
        return false;
    }

    /**
     * Checks if symbol is open bracket
     */
    boolean isOpenBracket(String symbol) {
        return symbol.equals(Bracket.OPEN.getSymbol());
    }

    /**
     * Checks if symbol is close bracket
     */
    boolean isCloseBracket(String symbol) {
        return symbol.equals(Bracket.CLOSE.getSymbol());
    }

    Operator getOperator(String symbol) throws OperatorNotFoundException {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator;
        throw new OperatorNotFoundException();
    }

    /**
     * Checks if symbol is operator and returns priority
     */
    int priority(String symbol) throws OperatorNotFoundException {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator.getPriority();
        throw new OperatorNotFoundException();
    }

    /**
     * Checks if symbol is the action and return the action
     */
    public UserAction getUserAction(String action) throws Exception {
        for (UserAction userAction : UserAction.values()) {
            if (userAction.getCommand().equals(action))
                return userAction;
        }
        throw new UserActionNotFoundException();
    }

    /**
     * Validates string and returns true if it is valid
     */
    public boolean isValidExpression(String expression) {
        String VALID_EXPRESSION = Operator.DOUBLE_DATA_TYPE + Operator.getAll() + Bracket.getAll() + "]*";
        String OPERATOR = "[" + Operator.getAll() + "]";
        boolean valid = false, hasSymbol = false;
        if (Pattern.matches(VALID_EXPRESSION, expression)) {
            valid = true;
            if (Character.toString(expression.charAt(0)).matches(OPERATOR) || Character.toString(expression.charAt(expression.length() - 1)).matches(OPERATOR)) {
                valid = false;
            }
            char[] cArray = expression.toCharArray();
            for (int i = 0; i < cArray.length; i++) {
                if (Character.toString(cArray[i]).matches(OPERATOR) && (Character.toString(cArray[i + 1]).matches(OPERATOR)
                        || Character.toString(cArray[i + 1]).matches(OPERATOR))) {
                    valid = false;
                }
                if(Character.toString(cArray[i]).matches(OPERATOR))
                hasSymbol = true;
            }
        }
        return hasSymbol ? valid : false;
    }
}
