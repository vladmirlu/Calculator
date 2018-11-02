package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;

/**
 * Decorate string before calculation
 */
public class MathDecorator {

    private final MathValidator validator;

    public MathDecorator(MathValidator validator) {
        this.validator = validator;
    }

    /**
     * Decorate entered string and returns new built ordered string
     */
    public String decorateAndBuild(String expression) throws OperatorNotFoundException {

        StringBuilder stack = new StringBuilder();
        MathElementsBuilder mathBuilder = new MathElementsBuilder(stack);
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
