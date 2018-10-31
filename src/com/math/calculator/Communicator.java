package com.math.calculator;

import com.math.calculator.calculation.Service;
import com.math.calculator.history.HistoryPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


class Communicator {

    private final Service service = new Service();

    private HistoryPrinter historyPrinter = new HistoryPrinter(service.getUsers());

    String inputData(String action) throws Throwable {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (action.equals("")) {
            System.out.println("The result = " + getCalcResult(br));
        }
        UserAction userAction = service.getValidator().getUserAction(getAction(br));
        switch (userAction) {
            case CALCULATE:
                System.out.println("The result = " + getCalcResult(br));
                return inputData(userAction.getCommand());
            case ALL_RESULTS:
                historyPrinter.printAllResults();
                return inputData(userAction.getCommand());
            case ALL_UNIQUE_RESULTS:
                historyPrinter.printAllUniqueResults();
                return inputData(userAction.getCommand());
        }
        return "Good bye";
    }

    private String getCalcResult(BufferedReader br) throws Throwable {

        System.out.println("Input please your name:");
        String username = br.readLine();
        System.out.println("Input please a math expression. \n Numbers only or math operators like + - * / ^ % ( )");
        return service.calculate(br.readLine(), username);
    }

    private String getAction(BufferedReader br) throws IOException {

        System.out.println("To calculate input '" + UserAction.CALCULATE.getCommand() + "'"
                + "\n To display all results history input '" + UserAction.ALL_RESULTS.getCommand() + "'"
                + "\n To display all unique results history input '" + UserAction.ALL_UNIQUE_RESULTS.getCommand() + "'"
                + "\n To display all results of user input '" + UserAction.USER_ALL_RESULTS.getCommand() + "'"
                + "\n To display all unique results of user input '" + UserAction.USER_UNIQUE_RESULTS.getCommand() + "'"
                + "\nTo exit press any other kay or press enter)");
        return br.readLine();
    }

    /*private void showAllUserResultsHistory(BufferedReader br) throws IOException {

        System.out.println("Input please your username:");
        String username = br.readLine();
        historyPrinter.printAllUserResults(username);
    }*/
}
