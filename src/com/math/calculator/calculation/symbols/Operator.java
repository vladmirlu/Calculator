package com.math.calculator.calculation.symbols;

/**
 * All math operators and their methods
 */
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

    /**
     * Symbol of operator
     */
    private final String symbol;
    /**
     * Operator priority
     */
    private final Integer priority;
    /**
     * Double data type regular expression for matching math expression
     */
    public static final String DOUBLE_DATA_TYPE = "[\\d+\\.?(\\d+)?\\";

    Operator(String symbol, Integer priority) {
        this.symbol = symbol;
        this.priority = priority;
    }

    /**
     * Returns operator symbol
     * @return symbol of operator
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Returns operator priority
     * @return priority of operator
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Abstract operator math operation which each operator overrides
     * @param x first operand
     * @param y second operand
     * @return  math operation result of x and y
     */
    public abstract double apply(double x, double y);

    /**
     * Get all operators symbols
     * @return all operators symbols as string
     */
    public static String getAll() {
        StringBuilder builder = new StringBuilder();
        for (Operator operator : values()) {
            builder.append(operator.getSymbol());
        }
        return builder.toString();
    }
}