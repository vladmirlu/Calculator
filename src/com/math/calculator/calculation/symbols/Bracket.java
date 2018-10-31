package com.math.calculator.calculation.symbols;

public enum Bracket {

    OPEN("("), CLOSE(")");

    private final String symbol;

    Bracket(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}

