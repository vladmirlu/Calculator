package com.math.calculator.calculation.symbols;

/**
 * Commands to use all calculator functions by user
 */
public enum UserAction {

    CALCULATE("c"), ALL_RESULTS("a"), ALL_UNIQUE_RESULTS("u"), USER_ALL_RESULTS("ua"), USER_UNIQUE_RESULTS("uu");

    private final String command;

    UserAction(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }
}
