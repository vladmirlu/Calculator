package com.math.calculator.calculation.operators;

public class Pov extends Operator{

    @Override
    public Double getMathResult(Double A, Double B, Character symbol) {
        return Math.pow(A, B);
    }

    @Override
    public Character getSign() {
        return '^';
    }

    @Override
    public Byte getPriority() {
        return 3;
    }

}
