package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;

public class MathDecorator {

    private final Validator validator;

    public MathDecorator(Validator validator) {
        this.validator = validator;
    }

    public String decorate(String expression) throws OperatorNotFoundException {

        StringBuilder stack = new StringBuilder();
        MathElementsCollector formatter = new MathElementsCollector(stack);
        StringBuilder elements = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            String element = Character.toString(expression.charAt(i));
            if (validator.isOperator(element)) {
                elements = formatter.orderOperators(element, validator, elements);
            } else if (validator.isOpenBracket(element)) {
                stack.append(element);
            } else if (validator.isCloseBracket(element)) {
                elements = formatter.orderElementsInBrackets(validator, elements);
            } else {
                elements.append(element); // Если символ не оператор - добавляем в выходную последовательность
            }
        }
        return formatter.getOrderedString(elements);
    }
}
