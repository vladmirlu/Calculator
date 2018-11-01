package com.math.calculator.calculation;

class MathElementsCollector {

    private final StringBuilder stack;

    public MathElementsCollector(StringBuilder stack){
        this.stack = stack;
    }

    /** Добавляет во входную строку операторы в приоритетном порядке*/
    StringBuilder orderOperators(String operator, Validator validator, StringBuilder elements) throws Exception {
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
    /** Добавляет елементы во входную строку пока следующий елемент не открывающая скобка*/
    StringBuilder orderElementsInBrackets(Validator validator, StringBuilder elements)  {

        String element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        while (!validator.isOpenBracket(element)) {
            elements.append(" ").append(element);
            stack.setLength(stack.length() - 1);
            element = Character.toString(stack.substring(stack.length() - 1).charAt(0));
        }
        stack.setLength(stack.length() - 1);
        return elements;
    }

    /** Если в стеке остались операторы, добавляет их в входную строку*/
    String getOrderedString(StringBuilder elements) {

        while (stack.length() > 0) {
            elements.append(" ").append(stack.substring(stack.length() - 1));
            stack.setLength(stack.length() - 1);
        }
        return elements.toString();
    }
}