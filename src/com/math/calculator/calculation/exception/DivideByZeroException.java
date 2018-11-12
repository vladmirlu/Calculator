package com.math.calculator.calculation.exception;

/** Signals that was an attempt to divide some number by zero*/
public class DivideByZeroException extends Exception  {

    public DivideByZeroException() {
        super.printStackTrace();
    }
}
