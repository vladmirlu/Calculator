package com.math.calculator.calculation.symbols;

/**
 * Open and close brackets
 */
public enum Bracket {

    OPEN("("), CLOSE(")");

    /**
     * Symbol of bracket
     */
    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    /**
     * Returns bracket symbol
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns both open and close brackets symbols
     *
     * @return all brackets
     */
    public static String getAll() {
        return OPEN.symbol + CLOSE.symbol;
    }
}

