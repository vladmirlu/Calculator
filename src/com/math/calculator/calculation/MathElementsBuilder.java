package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;

/**
 * Adds elements to building string in priority order
 **/
class MathElementsBuilder {

    private final StringBuilder stack;

    public MathElementsBuilder(StringBuilder stack) {
        this.stack = stack;
    }

    /**
     * Adds operators to building string in priority order
     */
    StringBuilder orderOperators(String operator, MathValidator validator, StringBuilder elements) throws OperatorNotFoundException {
        while (stack.length() > 0) {
            String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
            if (validator.isOperator(element) && (validator.priority(operator) <= validator.priority(element))) {
                elements.append(" ").append(element).append(" ");
                stack.setLength(stack.length() - 1);
            } else {
                elements.append(" ");
                break;
            }
        }
        elements.append(" ");
        stack.append(operator);
        return elements;
    }

    /**
     * Adds elements to building string until element is not open bracket
     */
    StringBuilder orderElementsInBrackets(MathValidator validator, StringBuilder elements) {

        String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        while (!validator.isOpenBracket(element)) {
            elements.append(" ").append(element);
            stack.setLength(stack.length() - 1);
            element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        }
        stack.setLength(stack.length() - 1);
        return elements;
    }

    /**
     * If there are any operators in the stack left adds them to building string
     */
    String getOrderedString(StringBuilder elements) {

        while (stack.length() > 0) {
            elements.append(" ").append(stack.substring(stack.length() - 1));
            stack.setLength(stack.length() - 1);
        }
        return elements.toString();
    }
}