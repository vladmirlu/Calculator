package com.math.calculator.calculation;

class MathStringDecorator {

    private final StringBuilder stack;

    public MathStringDecorator(StringBuilder stack){
        this.stack = stack;
    }
    /** Добавляет во входную строку операторы в приоритетном порядке*/
    StringBuilder orderOperators(String operator, Validator validator, StringBuilder sbOut) throws Exception {
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
        return sbOut;
    }
    /** Добавляет елементы во входную строку пока следующий елемент не открывающая скобка*/
    StringBuilder orderElementsInBrackets(Validator validator, StringBuilder sbOut) {

        String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        while (!validator.isOpenBracket(element)) {
            if (stack.length() < 1) {
                System.out.println("Error! Check if the math expression is wright.");
            }
            sbOut.append(" ").append(element);
            stack.setLength(stack.length() - 1);
            element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        }
        stack.setLength(stack.length() - 1);
        return sbOut;
    }

    /** Если в стеке остались операторы, добавляет их в входную строку*/
    String getOrderedString(StringBuilder sbOut) {

        while (stack.length() > 0) {
            sbOut.append(" ").append(stack.substring(stack.length() - 1));
            stack.setLength(stack.length() - 1);
        }
        return sbOut.toString();
    }

    StringBuilder getStack() {
        return stack;
    }
}
