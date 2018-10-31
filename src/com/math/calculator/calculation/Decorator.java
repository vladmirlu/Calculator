package com.math.calculator.calculation;

public class Decorator {

    private final Validator validator;




    public Decorator(Validator validator) {
        this.validator = validator;
    }

    public String decorate(String expression) throws Exception {

        SymbolsFormatter formatter = new SymbolsFormatter(new StringBuilder(), new StringBuilder());

        for (int i = 0; i < expression.length(); i++) {
            String symbol = Character.toString(expression.charAt(i));
            if (validator.isOperator(symbol)) {
                formatter.moveOperator(symbol, validator);
            } else if (validator.isOpenBracket(symbol)) {
                formatter.getStack().append(symbol);
            } else if (validator.isCloseBracket(symbol)) {
                formatter.moveOpenBracket(validator);
            } else
                formatter.getSbOut().append(symbol); // Если символ не оператор - добавляем в выходную последовательность
        }
        return formatter.getOrderedString();
    }
}
