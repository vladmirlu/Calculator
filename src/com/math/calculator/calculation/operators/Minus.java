package com.math.calculator.calculation.operators;

public class Minus extends Operator {

    @Override
    public Double getMathResult(Double A, Double B, Character symbol) {
        return A - B;
    }

    @Override
    public Character getSign() {
        return '-';
    }

    @Override
    public Byte getPriority() {
        return 1;
    }
}
