package com.math.calculator.calculation.symbols;

public enum Operator {

    PLUS("+", 1) {
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-", 1) {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*", 2) {
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/", 2) {
        public double apply(double x, double y) {
            return x / y;
        }
    },
    MOD_DIVIDE("%", 2) {
        public double apply(double x, double y) {
            return x % y;
        }
    };

    private final String symbol;
    private final Integer priority;

    Operator(String symbol, Integer priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    public String getSymbol() {
        return symbol;
    }

    public Integer getPriority() {
        return priority;
    }

    public abstract double apply(double x, double y);
}