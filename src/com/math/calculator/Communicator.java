package com.math.calculator;

import com.math.calculator.calculation.MathService;
import com.math.calculator.calculation.symbols.UserAction;
import com.math.calculator.history.HistoryPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Communicates with user receives and shows data
 */
class Communicator {

    private final MathService mathService = new MathService();

    private HistoryPrinter historyPrinter = new HistoryPrinter(mathService.getUsers());

    /**
     * Opens console to communicate with user
     */
    String openConsole(String action) throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (action.equals("")) {
            System.out.println("The result = " + getCalcResult(br));
        }
        UserAction userAction = mathService.getValidator().getUserAction(getAction(br));
        switch (userAction) {
            case CALCULATE:
                System.out.println("The result = " + getCalcResult(br));
                return openConsole(userAction.getCommand());
            case ALL_RESULTS:
                historyPrinter.printAllResults();
                return openConsole(userAction.getCommand());
            case ALL_UNIQUE_RESULTS:
                historyPrinter.printAllUniqueResults();
                return openConsole(userAction.getCommand());
            case USER_ALL_RESULTS:
                System.out.println("Input please username:");
                System.out.println(historyPrinter.getUserAllResults(br.readLine()));
                return openConsole(userAction.getCommand());
            case USER_UNIQUE_RESULTS:
                System.out.println("Input please username:");
                System.out.println(historyPrinter.getUserUniqueResults(br.readLine()));
                return openConsole(userAction.getCommand());
        }
        return "Good bye";
    }

    /**
     * Receives data from user and returns the result
     */
    private String getCalcResult(BufferedReader br) throws Throwable {

        System.out.println("Input please your name:");
        String username = br.readLine();
        System.out.println("Input please a math expression. \n Numbers only or math operators like + - * / ^ % ( )");
        String expression = br.readLine();
        return mathService.calculateAndSave(expression, username);
    }

    /**
     * Opens command panel and returns some chosen action
     */
    private String getAction(BufferedReader br) throws IOException {

        System.out.println("To calculate input '" + UserAction.CALCULATE.getCommand() + "'"
                + "\n To display all results history input '" + UserAction.ALL_RESULTS.getCommand() + "'"
                + "\n To display all unique results history input '" + UserAction.ALL_UNIQUE_RESULTS.getCommand() + "'"
                + "\n To display all results of user input '" + UserAction.USER_ALL_RESULTS.getCommand() + "'"
                + "\n To display all unique results of user input '" + UserAction.USER_UNIQUE_RESULTS.getCommand() + "'"
                + "\nTo exit press any other kay or press enter)");
        return br.readLine();
    }
}
