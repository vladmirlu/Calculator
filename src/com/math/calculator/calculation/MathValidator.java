package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.InvalidMathExpressionException;
import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.symbols.Bracket;
import com.math.calculator.calculation.symbols.Operator;

import java.util.regex.Pattern;

/**
 * Validates expression and symbols
 */
public class MathValidator {

    /**
     * Checks if symbol is operator
     *
     * @param symbol math symbol
     * @return true if symbol is operator or false if no
     */
    boolean isOperator(String symbol) {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return true;
        return false;
    }

    /**
     * Checks if symbol is open bracket
     *
     * @param symbol math symbol
     * @return true if symbol is open bracket or false if no
     */
    boolean isOpenBracket(String symbol) {
        return symbol.equals(Bracket.OPEN.getSymbol());
    }

    /**
     * Checks if symbol is close bracket
     *
     * @param symbol math symbol
     * @return true if symbol is close bracket or false if no
     */
    boolean isCloseBracket(String symbol) {
        return symbol.equals(Bracket.CLOSE.getSymbol());
    }

    /**
     * Finds operator by symbol
     *
     * @param symbol math symbol
     * @return math operator
     * @throws OperatorNotFoundException when symbol is not operator
     */
    Operator findOperator(String symbol) throws OperatorNotFoundException {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator;
        throw new OperatorNotFoundException();
    }

    /**
     * Finds operator by symbol and returns his priority
     *
     * @param symbol math symbol
     * @return operator priority
     * @throws OperatorNotFoundException when symbol is not operator
     */
    int getPriority(String symbol) throws OperatorNotFoundException {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator.getPriority();
        throw new OperatorNotFoundException();
    }

    /**
     * Validates string and returns true if it is valid
     *
     * @param expression some entered expression
     * @return  validated math expression if it is valid
     * @throws InvalidMathExpressionException when entered expression is mathematically wrong
     */
    public String getValidatedExpression(String expression) throws InvalidMathExpressionException{
        String validExpression = Operator.DOUBLE_DATA_TYPE + Operator.getAll() + Bracket.getAll() + "]*";
        String operator = "[" + Operator.getAll() + "]";
        boolean valid = false, hasSymbol = false;
        if (Pattern.matches(validExpression, expression)) {
            valid = true;
            if (Character.toString(expression.charAt(0)).matches(operator) || Character.toString(expression.charAt(expression.length() - 1)).matches(operator)) {
                valid = false;
            }
            char[] cArray = expression.toCharArray();
            for (int i = 0; i < cArray.length; i++) {
                if (Character.toString(cArray[i]).matches(operator) && (Character.toString(cArray[i + 1]).matches(operator)
                        || Character.toString(cArray[i + 1]).matches(operator))) {
                    valid = false;
                }
                if(Character.toString(cArray[i]).matches(operator))
                hasSymbol = true;
            }
        }
        if(hasSymbol && valid) return expression;
        else throw new InvalidMathExpressionException();
    }
}
