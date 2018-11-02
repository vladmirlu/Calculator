package com.math.calculator;

import com.math.calculator.calculation.MathService;
import com.math.calculator.calculation.exception.UserActionNotFoundException;
import com.math.calculator.calculation.symbols.UserAction;
import com.math.calculator.history.HistoryPrinter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Communicates with user: receives and shows data.
 */
class Communicator {

    private final MathService mathService = new MathService();

    private HistoryPrinter historyPrinter = new HistoryPrinter(mathService.getUsers());

    /**
     * Opens console to communicate with user
     *
     * @param action: action name
     * @return an exit string
     * @throws Exception when program crashes
     */
    String openConsole(String action) throws Exception {
        try{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        if (action.equals("")) {
            System.out.println("The result = " + getCalcResult(br));
        }
            UserAction userAction = getAction(br);
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
            case EXIT:
                return "Good bye";
        }
    }catch (UserActionNotFoundException e){
        System.out.println("Error! " + "Command is invalid! Please choose one of command from the list below");
    }
        return openConsole("Error");
    }

    /**
     * Receives data from user and returns the result
     */
    private String getCalcResult(BufferedReader br) throws IOException {

        System.out.println("Input please your name:");
        String username = br.readLine();
        System.out.println("Input please a math expression. \n Numbers only or math operators like + - * / ^ % ( )");
        String expression = br.readLine();
        return mathService.calculateAndSave(expression, username);
    }

    /**
     * Opens command panel and returns some chosen action
     */
    private UserAction getAction(BufferedReader br) throws IOException, UserActionNotFoundException {

        System.out.println( UserAction.CALCULATE.getMessage() + UserAction.CALCULATE.getCommand()
                + "\n" + UserAction.ALL_RESULTS.getMessage() + UserAction.ALL_RESULTS.getCommand()
                + "\n" + UserAction.ALL_UNIQUE_RESULTS.getMessage() + UserAction.ALL_UNIQUE_RESULTS.getCommand()
                + "\n" + UserAction.USER_ALL_RESULTS.getMessage() + UserAction.USER_ALL_RESULTS.getCommand()
                + "\n" + UserAction.USER_UNIQUE_RESULTS.getMessage() + UserAction.USER_UNIQUE_RESULTS.getCommand()
                + "\n" + UserAction.EXIT.getMessage() + UserAction.EXIT.getCommand() );
        return UserAction.getUserAction(br.readLine());
    }
}
