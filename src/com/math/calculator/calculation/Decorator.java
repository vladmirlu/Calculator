package com.math.calculator.calculation;

public class Decorator {

    private final Validator validator;

    public Decorator(Validator validator) {
        this.validator = validator;
    }

    public String decorate(String expression) throws Exception {

        MathStringDecorator formatter = new MathStringDecorator(new StringBuilder());
        StringBuilder sbOut = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            String point = Character.toString(expression.charAt(i));
            if (validator.isOperator(point)) {
                sbOut = formatter.orderOperators(point, validator, sbOut);
            } else if (validator.isOpenBracket(point)) {
                formatter.getStack().append(point);
            } else if (validator.isCloseBracket(point)) {
                sbOut = formatter.orderElementsInBrackets(validator, sbOut);
            } else {
                sbOut.append(point); // Если символ не оператор - добавляем в выходную последовательность
            }
        }
        return formatter.getOrderedString(sbOut);
    }
}
