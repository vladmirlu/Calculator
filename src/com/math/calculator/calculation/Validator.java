package com.math.calculator.calculation;

import com.math.calculator.calculation.operators.brackets.CloseBracket;
import com.math.calculator.calculation.operators.brackets.OpenBracket;
import com.math.calculator.calculation.exception.OperatorNotFoundException;
import com.math.calculator.calculation.operators.*;

import java.util.Arrays;
import java.util.List;

public class Validator {

    private List<Operator> operators = Arrays.asList(new Plus(), new Minus(), new Multiply(), new Divide(), new ModDivide(), new Pov());

    boolean isOperator(Character symbol) {
        for (Operator oper : operators)
            if (oper.getSign() == symbol) return true;
        return false;
    }

    boolean isOpenBracket(Character symbol) {
        return symbol == OpenBracket.SIGN;
    }

    boolean isCloseBracket(Character symbol) {
        return symbol == CloseBracket.SIGN;
    }

    Operator getOperator(Character sign) throws Exception {
        for (Operator oper : operators)
            if (oper.getSign() == sign) return oper;
        throw new OperatorNotFoundException();
    }

    byte priority(Character operator) throws Exception {
        for (Operator oper : operators)
            if (oper.getSign() == operator) return oper.getPriority();
        throw new OperatorNotFoundException();
    }
}
