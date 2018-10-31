package com.math.calculator.calculation;

import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.symbols.Bracket;
import com.math.calculator.calculation.symbols.Operator;


public class Validator {

    boolean isOperator(String symbol) {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return true;
        return false;
    }

    boolean isOpenBracket(String symbol) {
        return symbol.equals(Bracket.OPEN.getSymbol());
    }

    boolean isCloseBracket(String symbol){
        return symbol.equals(Bracket.CLOSE.getSymbol());
    }

    Operator getOperator(String symbol) throws Exception {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator;
        throw new OperatorNotFoundException();
    }

    int priority(String symbol) throws Exception {
        for (Operator operator : Operator.values())
            if (operator.getSymbol().equals(symbol)) return operator.getPriority();
        throw new OperatorNotFoundException();
    }
}
