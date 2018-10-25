package com.math.calculator.calculation.operators;

public abstract class Operator {
   public abstract Character getSign();
   public abstract Byte getPriority();
   public abstract Double getMathResult(Double A, Double B, Character symbol);
}
