package com.math.calculator;

import com.math.calculator.calculation.Decorator;
import com.math.calculator.calculation.Service;
import com.math.calculator.calculation.Validator;
import com.math.calculator.history.HistoryPrintWriter;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class Communicator {

    private Validator validator = new Validator();

    private Decorator decorator = new Decorator(validator);

    private HistoryPrintWriter historyPrintWriter = new HistoryPrintWriter();

    private Service service = new Service();

    void inputData() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input please your name:");
        String username = br.readLine();
        System.out.println("Input please a math expression  (it should be numbers only or math operators like + - * / ^ % ( )):");
        String expression = br.readLine();
        expression = decorator.decorate(expression);
        Double result = service.calculate(expression);
        System.out.println(result);
        historyPrintWriter.createNewNote(username, expression, result);
        System.out.println("To continue calculation press 'y'");
        System.out.println("To display history  press 'h'");
        System.out.println("To exit press any other kay or press enter)");
        expression = br.readLine();

        if (expression.equalsIgnoreCase("y")) {
            inputData();
        } else if(expression.equalsIgnoreCase("h")) {
            historyPrintWriter.printResults();
        }
    }
}
