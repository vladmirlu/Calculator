package com.math.calculator.calculation.symbols;

/**
 * Open and close brackets
 */
public enum Bracket {

    OPEN("("), CLOSE(")");

    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static String getAll() {
        return OPEN.symbol + CLOSE.symbol;
    }
}

