package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;

/**
 * Decorate string before calculation
 */
public class MathStringBuilder {

    private final MathValidator validator;

    public MathStringBuilder(MathValidator validator) {
        this.validator = validator;
    }

    /**
     * Checks entered string and returns new built ordered string
     *
     * @param expression: entered math expression
     * @return built with getPriority order new string
     * @throws OperatorNotFoundException when method checks if the symbol is some operator, open or close bracket
     */
    public String buildOrderedMathString(String expression) throws OperatorNotFoundException {

        StringBuilder stack = new StringBuilder();
        MathSymbolsCollector mathBuilder = new MathSymbolsCollector(stack);
        StringBuilder priorBuild = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            String element = Character.toString(expression.charAt(i));
            if (validator.isOperator(element)) {
                priorBuild = mathBuilder.orderOperators(element, validator, priorBuild);
            } else if (validator.isOpenBracket(element)) {
                stack.append(element);
            } else if (validator.isCloseBracket(element)) {
                priorBuild = mathBuilder.orderElementsInBrackets(validator, priorBuild);
            } else {
                priorBuild.append(element);
            }
        }
        return mathBuilder.getOrderedString(priorBuild);
    }
}
