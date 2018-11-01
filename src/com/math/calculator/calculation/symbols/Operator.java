package com.math.calculator.calculation.symbols;

public enum Operator {

    MINUS("-", 1) {
        public double apply(double x, double y) {
            return x - y;
        }
    },
    PLUS("+", 1) {
        public double apply(double x, double y) {
            return x + y;
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
    },
    POV("^", 2) {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    };

    private final String symbol;
    private final Integer priority;
    public static final String DATA_TYPE = "[\\d+\\.?(\\d+)?\\";

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

    public static String getAll(){
        StringBuilder builder = new StringBuilder();
        for(Operator operator: values()){
            builder.append(operator.getSymbol());
        }
        return builder.toString();
    }
}