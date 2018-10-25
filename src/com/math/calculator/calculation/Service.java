package com.math.calculator.calculation;

import com.math.calculator.calculation.operators.Operator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Service {

    private Validator validator = new Validator();

    public Double calculate(String sIn) throws Exception {
        double A, B;
        String temp;
        Operator oper;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer tokenizer = new StringTokenizer(sIn);
        while (tokenizer.hasMoreTokens()) {
            try {
                temp = tokenizer.nextToken().trim();
                if (temp.length() == 1 && validator.isOperator(temp.charAt(0))) {
                    oper = validator.getOperator(temp.charAt(0));
                    if (stack.size() < 2) throw new Exception("Wrong data quantity" + temp);
                    B = stack.pop();
                    A = stack.pop();
                    A = oper.getMathResult(A, B, temp.charAt(0));
                    stack.push(A);
                } else {
                    A = Double.parseDouble(temp);
                    stack.push(A);
                }
            } catch (Exception e) {
                throw new Exception("Invalid math symbol");
            }
        }
        if (stack.size() > 1) throw new Exception("Operators quantity doesn't match operands quantity");
        return stack.pop();
    }
}
