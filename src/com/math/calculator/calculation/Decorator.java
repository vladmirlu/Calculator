package com.math.calculator.calculation;

public class Decorator {

    private final Validator validator;

    public Decorator(Validator validator) {
        this.validator = validator;
    }

    public String decorate(String expression) throws Exception {

        SymbolsFormatter formatter = new SymbolsFormatter();

        for (int i = 0; i < expression.length(); i++) {
            char cIn = expression.charAt(i);
            if (validator.isOperator(cIn)) {
                formatter.moveOperator(cIn);
            } else if (validator.isOpenBracket(cIn)) formatter.getStack().append(cIn);
            else if (validator.isCloseBracket(cIn)) formatter.moveOpenBracket();
            else formatter.getSbOut().append(cIn); // Если символ не оператор - добавляем в выходную последовательность
        }

        return formatter.getOrderedString();
    }
}
