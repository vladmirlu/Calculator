package com.math.calculator.calculation.exception;

/** Signals that the entered console command not found. Actually because the entered string is not a command*/
public class UserActionNotFoundException extends Exception{

    public UserActionNotFoundException () {
        super.printStackTrace();
    }
}
