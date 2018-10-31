package com.math.calculator.calculation;

class SymbolsFormatter {

    private final StringBuilder stack;
    private final StringBuilder sbOut;

    public SymbolsFormatter(StringBuilder stack, StringBuilder sbOut){
        this.stack = stack;
        this.sbOut = sbOut;
    }

    void moveOperator(String operator, Validator validator) throws Exception {
        while (stack.length() > 0) {
            String temp = Character.toString(stack.substring(stack.length() - 1).charAt(0));
            if (validator.isOperator(temp) && (validator.priority(operator) <= validator.priority(temp))) {
                sbOut.append(" ").append(temp).append(" ");
                stack.setLength(stack.length() - 1);
            } else {
                sbOut.append(" ");
                break;
            }
        }
        sbOut.append(" ");
        stack.append(operator);
    }

    void moveOpenBracket(Validator validator) {
        String temp = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        while (!validator.isOpenBracket(temp)) {
            if (stack.length() < 1)
                System.out.println("Error! Check if the math expression is wright.");
            sbOut.append(" ").append(temp);
            stack.setLength(stack.length() - 1);
            temp = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        }
        stack.setLength(stack.length() - 1);
    }

    /** Если в стеке остались операторы, добавляем их в входную строку*/
    String getOrderedString() {
        while (stack.length() > 0) {
            sbOut.append(" ").append(stack.substring(stack.length() - 1));
            stack.setLength(stack.length() - 1);
        }
        return sbOut.toString();
    }

    StringBuilder getStack() {
        return stack;
    }

    StringBuilder getSbOut() {
        return sbOut;
    }
}
