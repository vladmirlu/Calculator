package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;

/**
 * Adds elements to building string in priority order
 **/
class MathSymbolsCollector {

    /**
     * Math buffer to keep math symbols and numbers
     */
    private final StringBuilder stack;

    public MathSymbolsCollector(StringBuilder stack) {
        this.stack = stack;
    }

    /**
     * Adds operators to building string in priority order
     *
     * @param operator   math symbol
     * @param validator  validator of math expression
     * @param priorBuild current build of math elements in priority order
     * @return modified with operators build of math elements
     * @throws OperatorNotFoundException when @param operator is not operator
     */
    StringBuilder orderOperators(String operator, MathValidator validator, StringBuilder priorBuild) throws OperatorNotFoundException {
        while (stack.length() > 0) {
            String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
            if (validator.isOperator(element) && (validator.getPriority(operator) <= validator.getPriority(element))) {
                priorBuild.append(" ").append(element).append(" ");
                stack.setLength(stack.length() - 1);
            } else {
                priorBuild.append(" ");
                break;
            }
        }
        priorBuild.append(" ");
        stack.append(operator);
        return priorBuild;
    }

    /**
     * Adds elements to building string until element is not open bracket
     *
     * @param validator  validator of math expression
     * @param priorBuild current build of math elements in priority order
     * @return modified with operators build of math elements
     */
    StringBuilder orderElementsInBrackets(MathValidator validator, StringBuilder priorBuild) {

        String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        while (!validator.isOpenBracket(element)) {
            priorBuild.append(" ").append(element);
            stack.setLength(stack.length() - 1);
            element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        }
        stack.setLength(stack.length() - 1);
        return priorBuild;
    }

    /**
     * If there are any operators in the stack left adds them to building string
     *
     * @param priorBuild current build of math elements in priority order
     * @return modified build of math elements
     */
    String getOrderedMathString(StringBuilder priorBuild) {

        while (stack.length() > 0) {
            priorBuild.append(" ").append(stack.substring(stack.length() - 1));
            stack.setLength(stack.length() - 1);
        }
        return priorBuild.toString();
    }
}